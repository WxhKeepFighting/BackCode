package com.wxh.musicsystem.service;

import com.wxh.musicsystem.dao.MusicRepository;
import com.wxh.musicsystem.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * 两条 sql 语句同时在一个方法中执行，为了防止一个 sql 语句执行成功而另一个 sql 语句执行失败，
 * 引入了事务管理，需要在方法上加 @Transactional事务注解
 * 事务确保了数据库数据的完整性和一致性
 * 事务管理测试
 * 两条数据同时成功，或者同时不成功
 * 保证数据库数据的完整性和一致性
 */
@Service
@Transactional//事务管理标签
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    public void deleteOne(int id) {
        Optional<Music> byId = musicRepository.findById(id);
        if (byId.isPresent()) {
            if (id == 1) {
                throw new RuntimeException("Id为1的作业不能删除");
            } else {
                musicRepository.deleteById(id);
            }
        } else {
            throw new RuntimeException("Id为" + id + "的数据不存在");
        }
    }

    public void deleteMany(List<Integer> id_List) {
        id_List.forEach(id -> {
                    Optional<Music> byId = musicRepository.findById(id);
                    if (byId.isPresent()) {
                        musicRepository.deleteById(id);
                    }
                }
        );
    }


//   public MusicService(MusicRepository musicRepository){
//       this.musicRepository = musicRepository;
//   }


//    public void insertTwo(Music... music){
//        for (Music m:
//             music) {
//            musicRepository.save(m);
//        }
//
//        Music musicA = new Music();
//        musicA.setMusicauthor("冷雨夜");
//        musicA.setMusicname("刘德华");
//        musicA.setComment("一首好听的歌");
////        musicA.setData("2019/10/27");
//        musicA.setStatus(true);
//        musicRepository.saveAndFlush(musicA);
//        Music musicB = new Music();
//        musicB.setMusicauthor("汪骁虎");
//        musicB.setMusicname("那个男人");
//        musicB.setComment("流行音乐");
////        musicB.setData("2019/10/27");
////        musicB.setId(1);
//        musicB.setStatus(true);
//        musicRepository.saveAndFlush(musicB);
//    }
}
