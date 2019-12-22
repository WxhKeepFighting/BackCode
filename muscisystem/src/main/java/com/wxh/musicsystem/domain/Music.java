package com.wxh.musicsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor//必须要有构造函数
@Entity//表明实体类
//@Table(name = "Musicsinfo")//指定表
public class Music {
    /*@Id注释一定要在id上面，下面数据的顺序最好和数据库中的顺序一样避免不必要的错误*/
    @Id//表示主键
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    private int id;//唯一表示
    @NotBlank(message = "名称不能为空")
    @Column(name = "musicname",nullable = false)
    private String name;//音乐名称
    @NotBlank(message = "作者名不能为空")
    @Column(name = "musicauthor")
    private String author;//音乐作者
    @NotNull(message = "二选一选一个啊！")
    private boolean status;//是否有资源
    @Size(max = 30,min = 5,message = "评论长度应该为5到30之间")
    private String comment;//评论
    @NotNull(message = "日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "release_date")
    private Date date;//发行日期
    private String file;//资源
    @Override
    public String toString() {
        return "我的id为"+this.getId();
    }
}
