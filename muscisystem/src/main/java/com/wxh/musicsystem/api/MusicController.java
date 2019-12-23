package com.wxh.musicsystem.api;

import com.wxh.musicsystem.domain.Music;
import com.wxh.musicsystem.domain.MusicService;
import com.wxh.musicsystem.exception.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
public class MusicController{
    @Autowired
    MusicService musicService;
    @Value("${mypro.download_pathname}")
    private String down_path;
    @Value("${mypro.upload_pathname}")
    private String up_path;

    //获取所有歌曲内容
    @GetMapping(value = "/musics")
    public @ResponseBody AjaxResponse musicList(){
        return AjaxResponse.success(musicService.findAll());
    }

    //添加一首歌，这里没有用到@RequestBody因为music类中file类型无法转化为string类型，@RequestBody作用是将前端传来的数据自动封装成json数组，所以出错，不加注解spring也可以自动转化解析为实体类型
    @PostMapping(value = "/musics")
    public ResponseEntity<Object> musicAdd(@Valid Music music, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getField()+","+bindingResult.getFieldError().getDefaultMessage());
        }
        musicService.addMusics(music);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    // 按照歌手名称来查找,注意由于同样是Get请求，所以请求地址栏发生了改变，同时参数为String类型
    @GetMapping("/musics/type/{name}")
    public @ResponseBody AjaxResponse musicListByMusicName(@PathVariable("name") String name){
        return AjaxResponse.success(musicService.findBymusicauthor(name));
    }

    //按照id查询
    @GetMapping("/musics/{id}")
    public @ResponseBody AjaxResponse findByMusicId(@PathVariable int id){
        return AjaxResponse.success(musicService.findById(id));
    }

    //根据id更新
    @PutMapping(value = "/musics/{id}")
    public @ResponseBody AjaxResponse musicUpdate(@PathVariable int id, Music music){
        musicService.musicUpdate(id, music);
        return AjaxResponse.success();
    }

    //下载文件
    @GetMapping("/musics/attachment/{id}")
    public @ResponseBody AjaxResponse download(@PathVariable int id){
        musicService.download(id);
        return AjaxResponse.success();
    }

    //根据id删除
    @DeleteMapping(value = "/musics/{id}")
    public @ResponseBody AjaxResponse musicsDelete(@PathVariable("id") Integer id){
        musicService.deleteOne(id);
        return AjaxResponse.success();
    }

    //批量删除
    @DeleteMapping("musics/batch_delete/{all_id}")
    public @ResponseBody AjaxResponse deleteBatch(@PathVariable List<Integer> all_id){
        musicService.deleteMany(all_id);
        return AjaxResponse.success();
    }

    //分页显示
    @GetMapping(path = "musics/page/{currentPage}/{pageSize}")
    public @ResponseBody AjaxResponse getAllMusicByPageAndSort(@PathVariable("currentPage") Integer currentPage,
                                                @PathVariable("pageSize") Integer pageSize){
        return AjaxResponse.success(musicService.findByPage(currentPage,pageSize));
    }


 /*   @ExceptionHandler(value = {InvalidMusicIdException.class, FileNotFoundException.class})//只能处理当前的Controller
    public ResponseEntity<String> handleException(Exception e) {
        //....
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("出错了");
    }*/
}