package com.wxh.musicsystem_teacher.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

//import javax.persistence.Entity;
//
//@Entity
@Data
@AllArgsConstructor
public class Music {
    @Min(1)//最小值为1
    private int id;//唯一表示
    @NotBlank// !="" || !=null
    @Size(max = 20,min = 5)
    private String musicname;//音乐名称
    private String musicauthor;//音乐作者
    private boolean status;//音乐类型
    private String description;//音乐描述
    private LocalDate release_date;//发行日期
    private String file;//描述文件的位置
//    @JsonFormat(pattern = "yyyy-MM-dd")用application/form-data传的存在一个转换的过程
    //    private int status;//是否添加

//    public Music(){}
//    public Music(int id, String musicname, String musicauthor, String musictype, LocalDate release_date, String description){
//        this.id = id;
//        this.musicname = musicname;
//        this.musicauthor = musicauthor;
//        this.musictype = musictype;
//        this.release_date = release_date;
//        this.description = description;
//    }
}