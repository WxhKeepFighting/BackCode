package com.wxh.musicsystem.domain;

import com.wxh.musicsystem.dao.MusicRepository;
import com.wxh.musicsystem.exception.CustomException;
import com.wxh.musicsystem.exception.CustomExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
    @Value("${mypro.download_pathname}")
    private String down_path;
    @Value("${mypro.upload_pathname}")
    private String up_path;

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

    public void addMusics(Music... musics) {
        for (Music music : musics) {
            musicRepository.save(music);
        }
    }

    public List<Music> findAll() {
        return musicRepository.findAll();
    }

    public List<Music> findById(int id) throws CustomException {
        List<Music> list = new ArrayList<>();
        Optional<Music> byId = musicRepository.findById(id);
        boolean present = byId.isPresent();
        if (!present) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "您输入的id号不存在,请重新输入");
        }
        list.add(byId.get());
        return list;
    }

    public List<Music> findBymusicauthor(String author) throws CustomException {
        if (author.equals("")) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "输入的歌手姓名不能为空，请正确输入");
        } else {
            List<Music> byAuthor = musicRepository.findByAuthor(author);
            if (byAuthor.size() <= 0) {
                throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "您输入的歌手不存在,请重新输入");
            }
            return byAuthor;
        }
    }

    public void musicUpdate(int id, Music music) throws CustomException{
        if (music == null){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"更新的对象不能为空");
        }else {
            music.setId(id);
            musicRepository.save(music);
        }
    }


    public Page<Music> findByPage(Integer currentPage, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize, sort);
        return musicRepository.findAll(pageRequest);
    }

    public void download(int id) throws CustomException{
        Optional<Music> byId = musicRepository.findById(id);
        if (byId.isPresent()){
            Music music = byId.get();
            String fileName = music.getFile();
            Resource resource = new FileSystemResource(up_path+fileName);
            try {
                File up_file = resource.getFile();
                File down_file = new File(down_path+fileName);
                FileInputStream fr = new FileInputStream(up_file);
                FileOutputStream fw = new FileOutputStream(down_file);
                int i;
                while ((i = fr.read())!=-1){
                    fw.write(i);//读一次写一次
                }
                fr.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"该音乐没有资源");
        }
    }

}
