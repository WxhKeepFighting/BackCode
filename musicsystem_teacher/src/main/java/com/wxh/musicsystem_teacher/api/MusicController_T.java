package com.wxh.musicsystem_teacher.api;

import com.wxh.musicsystem_teacher.domain.Music;
import com.wxh.musicsystem_teacher.domain.InvalidMusicIdException;
import com.wxh.musicsystem_teacher.domain.MusicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.*;
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
        musicList.add(new Music(1, "那个男人", "杨宗纬", true, "好听的歌", LocalDate.now(), null));
        musicList.add(Music.builder().id(2).musicname("华夏").musicauthor("GAI周延").status(true).release_date(LocalDate.now()).description("好听的歌").file(null).build());
        musicList.add(Music.builder().id(3).musicname("画").musicauthor("邓紫棋").status(false).release_date(LocalDate.now()).description("真的好听").file(null).build());
        musicList.add(Music.builder().id(4).musicname("泡沫").musicauthor("邓紫棋").status(true).release_date(LocalDate.now()).description("真的好听").file("ab.txt").build());
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

        for (int i = 0; i < musicList.size(); i++) {//不能有重复项出现
            if (musicList.get(i).getId() == music.getId()) {
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

    @GetMapping("/musics/attachment/{id}")//点击前端链接获得请求
    public ResponseEntity<Resource> download(@PathVariable int id) {
        Music ObjectMusic = musicList.stream().filter(
                music -> music.getId() == id
        ).findFirst().orElse(null);
        if (ObjectMusic != null) {
            String FileName = ObjectMusic.getFile();
            System.out.println("文件名为" + FileName);
            Resource resource = new FileSystemResource("F:\\upload\\" + FileName);
            try {
                File up_file = resource.getFile();//获得下载文件
                File down_file = new File("F:\\download\\"+FileName);//创建文件然后再向指定文件夹里面写入文件
                FileOutputStream fw = new FileOutputStream(down_file);
                FileInputStream fr = new FileInputStream(up_file);
                int i;
                while ((i = fr.read())!=-1){
                    fw.write(i);
                }
                fw.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(resource);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("musics/type/{name}")
    public ResponseEntity<List<Music>> findByName(@PathVariable String name){

        List<Music> type_List = new ArrayList<>();

        musicList = musicList.stream().map(music -> {
            if (music.getMusicauthor().equals(name)){
                type_List.add(music);
                return music;
            }else {
                return music;
            }
        }).collect(Collectors.toList());
        if (type_List.size() != 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(type_List);
        }else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("musics/{id}")
    public ResponseEntity<List<Music>> findById(@PathVariable int id){
        List<Music> id_List = new ArrayList<>();
        Music Id_music = musicList.stream().filter(music -> music.getId()==id).findFirst().orElse(null);
        if (Id_music!=null){
            System.out.println("查找成功"+Id_music.getMusicname());
            id_List.add(Id_music);
            return ResponseEntity.status(HttpStatus.CREATED).body(id_List);
        }else {
            System.out.println("查找失败");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @ExceptionHandler(value = {InvalidMusicIdException.class, FileNotFoundException.class})//只能处理当前的Controller
    public ResponseEntity<String> handleException(Exception e) {
        //....
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("出错了");
    }
}
