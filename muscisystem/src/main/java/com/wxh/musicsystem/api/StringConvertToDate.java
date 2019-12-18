package com.wxh.musicsystem.api;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class StringConvertToDate implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        return Date.valueOf(s);
    }
}
