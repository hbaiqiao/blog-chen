package com.evan.wj.service;

import com.evan.wj.dao.PhotoHeightDAO;

import com.evan.wj.pojo.PhotoHeight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoHeightService {
    @Autowired
    PhotoHeightDAO photoHeightDAO;



    public List<PhotoHeight> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return photoHeightDAO.findAll(sort);
    }

    public Page listPage(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return photoHeightDAO.findAll(PageRequest.of(page, size, sort));
    }

    public List<PhotoHeight> SearchAuthor(String author) {
        return photoHeightDAO.findByAuthor(author);
    }

    public PhotoHeight SearchPhotoHeight(int id){return photoHeightDAO.findById(id);}



    public void addOrUpdatePhotoHeight(PhotoHeight photoHeight) {
        photoHeightDAO.save(photoHeight);
    }

    public PhotoHeight DelPhotoWidth(int id) { return photoHeightDAO.deleteById(id);}
}
