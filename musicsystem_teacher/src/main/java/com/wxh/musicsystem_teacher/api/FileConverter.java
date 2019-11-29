package com.wxh.musicsystem_teacher.api;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

/*
* 因为在前台提交数据的时候传过来的是一个file文件类型，但是后台数据库存放的是一个String类型的
* ，所以涉及到一个类型转换，springboot会自动调用这些组件进行类型转换*/
@Component
public class FileConverter implements Converter<MultipartFile, String > {

    String fileName;
    @Override//将multipartFile转换为String
    public String convert(MultipartFile multipartFile) {
        try {
            fileName = multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File("F:\\upload\\"+fileName));
        } catch (IllegalStateException|IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
