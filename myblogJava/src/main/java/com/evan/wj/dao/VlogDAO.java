package com.evan.wj.dao;


import com.evan.wj.pojo.Vlog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VlogDAO extends JpaRepository<Vlog,Integer> {

    //根据视频id查找
    Vlog findById(int id);

    //根据用户查找
    List<Vlog> findByAuthor(String author);

    //查找标题模糊搜索
    List<Vlog> findAllByTitleLike(String title);

    //删除帖子
    Vlog deleteById(int id);

}
