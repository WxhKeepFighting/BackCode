package com.wxh.musicsystem_teacher;

import com.wxh.musicsystem_teacher.domain.Music;
import org.springframework.beans.factory.support.ManagedList;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class ListSort {
    public static void main(String[] args) {
        List<Music> musicList = new ManagedList<>();
        musicList.add(new Music(1, "hello", "wxh", false, "好听的歌", LocalDate.now(), null));
        musicList.add(Music.builder().id(57).musicname("wxh55").musicauthor("sfsf").status(false).release_date(LocalDate.now()).description("111").file(null).build());
        musicList.add(Music.builder().id(6).musicname("name01").musicauthor("sfsf").status(false).release_date(LocalDate.now()).description("111").file(null).build());
        musicList.sort(Comparator.naturalOrder());
        for (Music a:
             musicList) {
            System.out.println(a.getId());
        }
    }
}
