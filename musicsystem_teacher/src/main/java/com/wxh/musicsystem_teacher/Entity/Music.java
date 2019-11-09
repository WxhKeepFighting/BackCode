package com.wxh.musicsystem_teacher.Entity;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Music {
    private int id;//唯一表示
    private String musicname;//音乐名称
    private String musicauthor;//音乐作者
    private String musictype;//音乐类型
    private String release_date;//发行日期
    //    private int status;//是否添加
    public Music(){}
    public Music(int id, String musicname, String musicauthor, String musictype, String release_date){
        this.id = id;
        this.musicname = musicname;
        this.musicauthor = musicauthor;
        this.musictype = musictype;
        this.release_date = release_date;
    }
}