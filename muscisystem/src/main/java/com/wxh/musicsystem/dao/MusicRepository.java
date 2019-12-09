package com.wxh.musicsystem.dao;

import com.wxh.musicsystem.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*首先创建一个接口MusicRepository，位于dao包下,MusicController调用该接口继承自JpaRepository的方法
，来实现和数据库交互*/
public interface MusicRepository extends JpaRepository<Music, Integer> {
    //idea能自动识别findBy后面的内容是否能在数据库中找到？
    List<Music> findBymusicauthor(String musicauthor);
//    List<Music> findByCommentGreaterThan
    int countBymusicauthor(String musicauthor);
    boolean existsBymusicauthor(String musicauthor);
    boolean deleteBymusicauthor(String musicauthor);
    Music findOneBymusicauthor(String musicauthor);
}
