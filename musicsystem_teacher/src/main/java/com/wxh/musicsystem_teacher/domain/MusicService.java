package com.wxh.musicsystem_teacher.domain;

import org.springframework.stereotype.Service;

@Service//500异常，不能将错误暴露给用户
public class MusicService {
    public void remove(int id){
        if (id == 1){
            throw new RuntimeException("id为1的作业不能删除");//用专业术语描述的，或者包含业务机密的信息
        }else{

        }
        //删除的业务在这儿
    }
}
