package com.wxh.musicsystem_teacher.Controller;

import com.wxh.musicsystem_teacher.Entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*不能用@Controller？？为什么*/
@RestController//400异常
@CrossOrigin//实现跨域访问
public class MusicController_T {

    private List<Music> musicList = new ArrayList<>();
    private MusicService musicService = new MusicService();

    @Autowired//可以省略
    public MusicController_T(MusicService musicService){
        this.musicService = musicService;
        musicList.add(new Music(1,"hello","wxh",false, "好听的歌", LocalDate.now(),null));
        musicList.add(new Music(2,"hello2","wxh2",false,"好听的歌", LocalDate.now(),null));
    }

    @GetMapping("/musics")//API命名方式
    public List<Music> findAll(){
        return musicList;
    }

    @PostMapping("/musics")
    public ResponseEntity<Object> create(@Valid Music music, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldErrors());
        }
        System.out.println(music.getId()+music.getMusicname());
        musicList.add(music);
        System.out.println("添加数据成功");
        return ResponseEntity.status(HttpStatus.CREATED).body(music.getFile());
    }

    @PutMapping("/musics/{id}")//修改数据
    public void update(@PathVariable int id, @RequestBody Music newMusic){
        //先找到老数据
        Music music = musicList.stream().filter(
                (music1) -> music1.getId() == id
        ).findFirst().orElse(null);

        musicList = musicList.stream().map(music1 -> {
            if (music1.getId() == music.getId()){
                return newMusic;
            }
            return music1;
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/musics/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        try{
            musicService.remove(id);
        }catch (Exception exception){
            return ResponseEntity.status(500).body("出错了!");
        }
        return ResponseEntity.ok().body("成功！");
//        musicList.removeIf(music -> music.getId() == id);
    }

//    @ExceptionHandler()//只能处理当前的Controller
//    public ResponseEntity<String> handleException(){
//
//    }
}
