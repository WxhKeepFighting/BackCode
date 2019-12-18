package com.wxh.musicsystem;

import com.wxh.musicsystem.dao.MusicRepository;
import com.wxh.musicsystem.domain.Music;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
class MusicsystemApplicationTests {
    @Autowired
    private MusicRepository musicRepository;

//    @After
//    public void clear(){
//        authorRepository.deleteAll();
//    }

    @Test
    public void find2(){
    }

}
