package com.evan.wj.dao;


import com.evan.wj.pojo.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemDAO extends JpaRepository<Item,Integer> {
//    Item findByTopic(String topic);

    //根据帖子id查找
    Item findById(int id);

    //根据用户id查找
    List<Item> findByUid(int uid);

    //根据帖子话题查找
    List<Item> findByTopic(String topic);
    //查找用户发表内容
    Item findByUsername(String username);

    //查找内容模糊搜索
    List<Item> findAllByInfoLike(String keywords);

    //删除帖子
    Item deleteById(int id);
    //    User getByUser_name(String user_name);
     //    Item getByNameAndPassword(String name,String password);
}
