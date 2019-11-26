package com.wxh.musicsystem_teacher.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

//import javax.persistence.Entity;
//
//@Entity
@Data//set get构造方法
@AllArgsConstructor//有参的构造函数
@NoArgsConstructor//无参
@Builder
public class Music implements Comparable<Music>{
    @Min(1)//最小值为1
    private int id;//唯一表示
    @NotBlank// !="" || !=null
    private String musicname;//音乐名称
    private String musicauthor;//音乐作者
    private boolean status;//音乐状态
    @Size(max = 20,min = 5)
    private String description;//音乐描述
    private LocalDate release_date;//发行日期
    private String file;//描述文件的名称

    @Override
    public int compareTo(Music musicm) {
        if (this.getId() >= musicm.getId()){
            return 1;
        }
        return -1;
    }
    /*
    *
    如果指定的数与参数相等返回0。

    如果指定的数小于参数返回 -1。

    如果指定的数大于参数返回 1。
*/

//    @JsonFormat(pattern = "yyyy-MM-dd")用application/form-data传的存在一个转换的过程
    //    private int status;//是否添加
}