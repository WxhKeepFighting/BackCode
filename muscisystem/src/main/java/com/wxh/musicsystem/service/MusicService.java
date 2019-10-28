package com.wxh.musicsystem.service;

import com.wxh.musicsystem.dao.MusicRepository;
import com.wxh.musicsystem.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *两条 sql 语句同时在一个方法中执行，为了防止一个 sql 语句执行成功而另一个 sql 语句执行失败，
 *引入了事务管理，需要在方法上加 @Transactional事务注解
 *事务确保了数据库数据的完整性和一致性
 */
@Service
public class MusicService {

   @Autowired
   private MusicRepository musicRepository;

    /**
     * 事务管理测试
     * 两条数据同时成功，或者同时不成功
     * 保证数据库数据的完整性和一致性
     */

    @Transactional//事务管理标签
    public void insertTwo(){
        Music musicA = new Music();
        musicA.setMusicauthor("邓悦");
        musicA.setMusicname("大耳朵图图");
        musicA.setMusictype("儿童");
//        musicA.setData("2019/10/27");
        musicA.setStatus(true);
        musicRepository.saveAndFlush(musicA);
        Music musicB = new Music();
        musicB.setMusicauthor("汪骁虎");
        musicB.setMusicname("那个男人");
        musicB.setMusictype("流行音乐");
//        musicB.setData("2019/10/27");
        musicB.setId(1);
        musicB.setStatus(true);
        musicRepository.saveAndFlush(musicB);
    }
}
