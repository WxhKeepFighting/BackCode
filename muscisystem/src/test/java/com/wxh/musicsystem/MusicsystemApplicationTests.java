package com.wxh.musicsystem;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class MusicsystemApplicationTests {
    @Resource
    MockMvc mockMvc;

    @Test
    void contextLoads() {

    }

}
