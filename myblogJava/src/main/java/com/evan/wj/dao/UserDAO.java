package com.evan.wj.dao;

import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByNum(String num);
    User findById(int id);
//    User getByUser_name(String user_name);
    User getByNumAndPassword(String num,String password);
    User getByNumAndName(String num,String name);
}
