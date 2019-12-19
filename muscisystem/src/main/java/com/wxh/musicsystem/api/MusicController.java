package com.wxh.musicsystem.api;

import com.wxh.musicsystem.dao.MusicRepository;
import com.wxh.musicsystem.domain.Music;
import com.wxh.musicsystem.domain.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class MusicController{
    @Autowired
    MusicService musicService;
    @Autowired
    MusicRepository repository;
    @Value("${mypro.download_pathname}")
    private String down_path;
    @Value("${mypro.upload_pathname}")
    private String up_path;

    //获取所有歌曲内容
    @GetMapping(value = "/musics")
    public List<Music> musicList(){
        return musicService.findAll();
    }

    //添加一首歌，这里没有用到@RequestBody因为music类中file类型无法转化为string类型，@RequestBody作用是将前端传来的数据自动封装成json数组，所以出错，不加注解spring也可以自动转化解析为实体类型
    @PostMapping(value = "/musics")
    public ResponseEntity<String> musicAdd(Music music){
        boolean flag = musicService.addMusics(music);
        if (flag){
            return ResponseEntity.status(HttpStatus.OK).body("添加成功");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("添加失败");
        }
    }

    // 按照歌手名称来查找,注意由于同样是Get请求，所以请求地址栏发生了改变，同时参数为String类型
    @GetMapping("/musics/type/{name}")
    public List<Music> musicListByMusicName(@PathVariable("name") String name){
        return musicService.findBymusicauthor(name);
    }

    //按照id查询
    @GetMapping("/musics/{id}")
    public List<Music> findByMusicId(@PathVariable int id){
        List<Music> mList = new ArrayList<>();
        Optional<Music> optionalMusic = musicService.findById(id);
        boolean flag = optionalMusic.isPresent();
        if (flag){
            mList.add(optionalMusic.get());
            return mList;
        }
        return null;
    }

    //根据id更新
    @PutMapping(value = "/musics/{id}")
    public ResponseEntity<String> musicUpdate(@PathVariable("id") int id, Music music){
        String flag;
        /*
         * 1、先找到老数据
         * 2、在旧的数据不为空的情况下，然后再更新数据
        */
        Optional<Music> byId = musicService.findById(id);
        if(byId.isPresent()){
            musicService.addMusics(music);
            flag = "success";
            return ResponseEntity.status(HttpStatus.OK).body(flag);
        }
        flag = "fail";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(flag);
    }

    //下载文件
    @GetMapping("/musics/attachment/{id}")
    public ResponseEntity<String> download(@PathVariable int id){
        Optional<Music> byId = musicService.findById(id);
        if (byId.isPresent()){
            Music music = byId.get();
            String fileName = music.getFile();
            Resource resource = new FileSystemResource(up_path+fileName);
            try {
                File up_file = resource.getFile();
                File down_file = new File(down_path+fileName);
                FileInputStream fr = new FileInputStream(up_file);
                FileOutputStream fw = new FileOutputStream(down_file);
                int i;
                while ((i = fr.read())!=-1){
                    fw.write(i);//读一次写一次
                }
                fr.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body("下载成功");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("下载失败");
    }

    //根据id删除
    @DeleteMapping(value = "/musics/{id}")
    public void musicsDelete(@PathVariable("id") Integer id){
        musicService.deleteOne(id);
    }

    //批量删除
    @DeleteMapping("musics/batch_delete/{all_id}")
    public void deletebatch(@PathVariable List<Integer> all_id){
        musicService.deleteMany(all_id);
    }

    @GetMapping(path = "musics/page/{currentPage}/{pageSize}")
    public Page<Music> getAllMusicByPageAndSort(@PathVariable("currentPage") Integer currentPage,
                                                @PathVariable("pageSize") Integer pageSize){
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        PageRequest pageRequest = PageRequest.of(currentPage-1, pageSize, sort);
        return repository.findAll(pageRequest);
    }
}