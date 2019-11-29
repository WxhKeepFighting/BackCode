package com.wxh.musicsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;//确保格式正确

@Data
@AllArgsConstructor
@Entity
@Table
public class Music {
    /*@Id注释一定要在id上面，下面数据的顺序最好和数据库中的顺序一样避免不必要的错误*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;//唯一表示
    @NotBlank
    private String musicname;//音乐名称
    private String musicauthor;//音乐作者
    private boolean status;//是否有资源
    @Size(max = 30,min = 5)
    private String comment;//评论
    @NotNull(message = "日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date release_date;//发行日期
    private String file;//资源

    //必须要有构造函数
    public Music(){}
    @Override
    public String toString() {
        return "我的id为"+this.getId();
    }
}
