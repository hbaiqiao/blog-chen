package com.evan.wj.service;

import com.evan.wj.dao.UserDAO;

import com.evan.wj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public boolean isExist(String num) {
        User user = userDAO.findByNum(num);
        return null!=user;
    }

    public List<User> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return userDAO.findAll(sort);
    }

    public User getUser(int id) {return  userDAO.findById(id);}

    public User Search(String num) {
        return userDAO.findByNum(num);
    }

    public User get(String num, String password){

        return userDAO.getByNumAndPassword(num, password);
    }
    public User getNumAndName(String num, String name){

        return userDAO.getByNumAndName(num, name);
    }

    public void addOrUpdateUser(User user) {
        userDAO.save(user);
    }
}
