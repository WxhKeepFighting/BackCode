package com.wxh.musicsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@AllArgsConstructor
@Entity//表明实体类
//@Table(name = "Musicsinfo")//指定表
public class Music {
    /*@Id注释一定要在id上面，下面数据的顺序最好和数据库中的顺序一样避免不必要的错误*/
    @Id//表示主键
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    private int id;//唯一表示
    @NotNull
    @Column(name = "musicname",nullable = false)
    private String name;//音乐名称
    @NotNull
    @Column(name = "musicauthor")
    private String author;//音乐作者
    @NotNull
    private boolean status;//是否有资源
    @Size(max = 30,min = 5)
    private String comment;//评论
    @NotNull(message = "日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "release_date")
    private Date date;//发行日期
    private String file;//资源

    //必须要有构造函数
    public Music(){}
    @Override
    public String toString() {
        return "我的id为"+this.getId();
    }
}
