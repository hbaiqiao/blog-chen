package com.evan.wj.dao;


import com.evan.wj.pojo.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicDAO extends JpaRepository<Music,Integer> {

    //根据音乐id查找
    Music findById(int id);

    //根据用户查找
    List<Music> findByAuthor(String author);

    //查找标题模糊搜索
    List<Music> findAllByTitleLike(String title);

    //删除帖子
    Music deleteById(int id);

}
