package com.wxh.musicsystem.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Date;//确保格式正确

@Data
@Entity
public class Music {
    /*@Id注释一定要在id上面，下面数据的顺序最好和数据库中的顺序一样避免不必要的错误*/
    @Id
    @GeneratedValue
    private int id;//唯一表示
    private String musicname;//音乐名称
    private String musicauthor;//音乐作者
    private String musictype;//音乐类型
    @NotNull(message = "日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;//发行日期
    private boolean status;//是否添加

    //必须要有构造函数
    public Music(){}
//    public Music(String musicname, String musicauthor,String musictype, String data, boolean status, int id){
//        this.musictype = musictype;
//        this.musicauthor = musicauthor;
//        this.musicname = musicname;
//        this.data = data;
//        this.status = status;
//        this.id = id;
//    }
    @Override
    public String toString() {
        return "我的id为"+this.getId();
    }
}
