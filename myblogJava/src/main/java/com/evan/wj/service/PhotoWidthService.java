package com.evan.wj.service;

import com.evan.wj.dao.PhotoWidthDAO;

import com.evan.wj.pojo.PhotoWidth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoWidthService {
    @Autowired
    PhotoWidthDAO photoWidthDAO;



    public List<PhotoWidth> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return photoWidthDAO.findAll(sort);
    }

    public Page listPage(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return photoWidthDAO.findAll(PageRequest.of(page, size, sort));
    }

    public List<PhotoWidth> SearchAuthor(String author) {
        return photoWidthDAO.findByAuthor(author);
    }

    public PhotoWidth SearchPhotoWidth(int id){return photoWidthDAO.findById(id);}



    public void addOrUpdatePhotoWidth(PhotoWidth photoWidth) {
        photoWidthDAO.save(photoWidth);
    }

    public PhotoWidth DelPhotoWidth(int id) { return photoWidthDAO.deleteById(id);}
}
