package com.wxh.musicsystem.domain;

import com.wxh.musicsystem.dao.MusicRepository;
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
    private MusicRepository musicRepository;//还可以通过构造方法传参

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

    public boolean addMusics(Music... musics) {
        for (Music music : musics) {
            Music music1 = musicRepository.save(music);
            if (music1.getName() == ""){
                return false;
            }
        }
        return true;
    }

    public List<Music> findAll() {
        return musicRepository.findAll();
    }

    public Optional<Music> findById(int id) {
        Optional<Music> byId = musicRepository.findById(id);
        if (byId.isPresent()) {
            return byId;
        } else {
            return Optional.empty();
        }
    }

    public List<Music> findBymusicauthor(String author) {
        return musicRepository.findByAuthor(author);
    }

}
