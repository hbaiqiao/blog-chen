package com.evan.wj.dao;


import com.evan.wj.pojo.PhotoHeight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoHeightDAO extends JpaRepository<PhotoHeight,Integer> {

    //根据id查找
    PhotoHeight findById(int id);

    //根据用户查找
    List<PhotoHeight> findByAuthor(String author);

    //删除帖子
    PhotoHeight deleteById(int id);

}
