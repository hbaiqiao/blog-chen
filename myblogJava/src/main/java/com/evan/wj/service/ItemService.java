package com.evan.wj.service;

import com.evan.wj.dao.ItemDAO;
import com.evan.wj.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemDAO itemDAO;



    public List<Item> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return itemDAO.findAll(sort);
    }

    public Item SearchUser(String username) {
        return itemDAO.findByUsername(username);
    }

    public Item SearchItem(int id){return itemDAO.findById(id);}

    public List<Item> SearchUid(int uid){return itemDAO.findByUid(uid);}

    public List<Item> SearchTopic(String topic) {return itemDAO.findByTopic(topic); }

    public List<Item> SearchInfo(String keywords) {
        return itemDAO.findAllByInfoLike( '%'+ keywords + '%');
    }

    public void addOrUpdateItem(Item item) {
        itemDAO.save(item);
    }

    public Item DelItem(int id) { return itemDAO.deleteById(id);}
}
