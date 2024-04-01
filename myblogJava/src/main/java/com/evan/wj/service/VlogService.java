package com.evan.wj.service;

import com.evan.wj.dao.VlogDAO;

import com.evan.wj.pojo.Vlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VlogService {
    @Autowired
    VlogDAO vlogDAO;



    public List<Vlog> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return vlogDAO.findAll(sort);
    }

    public Page listPage(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return vlogDAO.findAll(PageRequest.of(page, size, sort));
    }

    public List<Vlog> SearchAuthor(String author) {
        return vlogDAO.findByAuthor(author);
    }

    public Vlog SearchVlog(int id){return vlogDAO.findById(id);}



    public List<Vlog> SearchTitle(String keywords) {
        return vlogDAO.findAllByTitleLike( '%'+ keywords + '%');
    }

    public void addOrUpdateVlog(Vlog vlog) {
        vlogDAO.save(vlog);
    }

    public Vlog DelVlog(int id) { return vlogDAO.deleteById(id);}
}
