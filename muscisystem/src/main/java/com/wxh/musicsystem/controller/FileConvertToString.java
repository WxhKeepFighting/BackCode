package com.wxh.musicsystem.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class FileConvertToString implements Converter<MultipartFile, String> {

    @Value("${mypro.pathname}")
    private String pathname;

    @Override
    public String convert(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(pathname+fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
