package com.evan.wj.dao;


import com.evan.wj.pojo.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogDAO extends JpaRepository<Blog,Integer> {
    //
    List<Blog> findAll();

    //根据帖子id查找
    Blog findById(int id);

    //根据用户查找
    List<Blog> findByAuthor(String author);

    //根据帖子话题查找
    List<Blog> findByType(String type);


    //查找内容模糊搜索
    List<Blog> findAllByContentLike(String content);

    //删除帖子
    Blog deleteById(int id);

}
