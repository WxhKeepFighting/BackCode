package com.wxh.musicsystem_teacher.Controller;

import com.wxh.musicsystem_teacher.Entity.Music;
import com.wxh.musicsystem_teacher.InvalidMusicIdException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*不能用@Controller？？为什么*/
@RestController//400异常
@CrossOrigin//实现跨域访问
@Slf4j//日志输出
public class MusicController_T {


//    private static final Logger logger = LogFactory.getLog(MusicController_T.class); @Slf4j代替一行代码

    private List<Music> musicList = new ArrayList<>();
    private MusicService musicService = new MusicService();

    @Autowired//可以省略
    public MusicController_T(MusicService musicService) {
        this.musicService = musicService;
        musicList.add(new Music(1, "hello", "wxh", false, "好听的歌", LocalDate.now(), null));
        musicList.add(Music.builder().id(57).musicname("wxh55").musicauthor("sfsf").status(false).release_date(LocalDate.now()).description("111").file(null).build());
        musicList.add(Music.builder().id(6).musicname("name01").musicauthor("sfsf").status(false).release_date(LocalDate.now()).description("111").file(null).build());
    }

    @GetMapping("/musics")//API命名方式
    public List<Music> findAll() {
        musicList.sort(Comparator.naturalOrder());
        return musicList;
    }

    @PostMapping("/musics")
    public ResponseEntity<Object> create(@Valid Music music, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldErrors());
        }

        for (int i = 0;i<musicList.size();i++){
            if (musicList.get(i).getId() == music.getId()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
            }
        }
        System.out.println(music.getId() + music.getMusicname());
        musicList.add(music);

        log.info("添加数据成功");//简化了操作
        return ResponseEntity.status(HttpStatus.CREATED).body(music.getFile());
    }

    @PutMapping("/musics/{id}")//修改数据
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Music newMusic) {
        //先找到老数据
        Music music = musicList.stream().filter(
                (music1) -> music1.getId() == id
        ).findFirst().orElse(null);
        if (music == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("作业不存在");
        }

        musicList = musicList.stream().map(music1 -> {
            if (music1.getId() == music.getId()) {
                return newMusic;
            }
            return music1;
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ok");
    }

    @DeleteMapping("/musics/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            musicService.remove(id);
        } catch (Exception exception) {
            return ResponseEntity.status(500).body("出错了!");
        }
        musicList.removeIf(music -> music.getId() == id);
        return ResponseEntity.ok().body("成功！");
    }

    @GetMapping("/musics/{id}/attachment")
    public ResponseEntity<Resource> download(@PathVariable int id) {
        Resource resource = new FileSystemResource("F:\\upload");
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @ExceptionHandler(value = {InvalidMusicIdException.class, FileNotFoundException.class})//只能处理当前的Controller
    public ResponseEntity<String> handleException(Exception e) {
        //....
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("出错了");
    }
}
