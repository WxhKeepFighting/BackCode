package com.wxh.musicsystem_teacher;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@Component
public class FileConverter implements Converter<MultipartFile, String > {

    @Override
    public String convert(MultipartFile multipartFile) {
        try {
            multipartFile.transferTo(new File("F:\\upload"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "abc.file";
    }
}
