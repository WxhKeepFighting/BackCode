package com.wxh.musicsystem.controller;

import com.wxh.musicsystem.entity.Music;
import com.wxh.musicsystem.entity.oldMusic;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*不能用@Controller？？为什么*/
@RestController
@CrossOrigin//实现跨域访问
public class oldMusController {

    private List<oldMusic> musicList = new ArrayList<>();

    public oldMusController(){
        musicList.add(new oldMusic(1,"hello","wxh","oldmusic","2019-11-8"));
    }

    @GetMapping("/musics")//得到数据
    public List<oldMusic> findAll(){
        return musicList;
    }

    @PostMapping("/musics")
    public void create(@RequestBody oldMusic music){
        musicList.add(music);
        System.out.println("添加数据成功");
    }

//    @PostMapping("/musics")//添加数据
//    public void create(@RequestParam String Musicauthor,
//                       @RequestParam String Musicname,
//                       @RequestParam String Data,
//                       @RequestParam String Musictype,
//                       @RequestParam boolean Status,
//                       @RequestParam int Id){
//        Music music = new Music();
//        music.setData(Data);
//        music.setId(Id);
//        music.setMusicauthor(Musicauthor);
//        music.setMusicname(Musicname);
//        music.setMusictype(Musictype);
//        music.setStatus(Status);
//        musicList.add(music);
//    }

    @PutMapping("/musics/{id}")//修改数据
    public void updata(@PathVariable int id, @RequestBody oldMusic newMusic){
        //先找到老数据
        oldMusic oldmusic = musicList.stream().filter(
                (music) -> music.getId() == id
        ).findFirst().orElse(null);
        System.out.println(oldmusic);

        //在旧的数据不为空的情况下，然后再更新数据
        for(int i = 0;i < musicList.size();i++){
            if (musicList.get(i).getId() == oldmusic.getId()){
                musicList.remove(musicList.get(i));
                musicList.add(newMusic);
            }
        }
        System.out.println(musicList);

//        musicList = musicList.stream().map(
//                music ->
//                {
//                    if (music.getId() == oldmusic.getId()){
//                        musicList.remove(music);
//                        musicList.add(newMusic);
//                    }
//                    return music;
//                }
//        ).collect(Collectors.toList());
//
//        System.out.println(musicList);

    }
}
