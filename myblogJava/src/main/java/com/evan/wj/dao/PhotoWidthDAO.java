package com.evan.wj.dao;


import com.evan.wj.pojo.PhotoWidth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoWidthDAO extends JpaRepository<PhotoWidth,Integer> {

    //根据id查找
    PhotoWidth findById(int id);

    //根据用户查找
    List<PhotoWidth> findByAuthor(String author);

    //删除帖子
    PhotoWidth deleteById(int id);

}
