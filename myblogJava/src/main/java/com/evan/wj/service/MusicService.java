package com.evan.wj.service;

import com.evan.wj.dao.MusicDAO;

import com.evan.wj.pojo.Music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {
    @Autowired
    MusicDAO musicDAO;



    public List<Music> list() {
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return musicDAO.findAll(sort);
    }

    public Page listPage(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return musicDAO.findAll(PageRequest.of(page, size, sort));
    }

    public List<Music> SearchAuthor(String author) {
        return musicDAO.findByAuthor(author);
    }

    public Music SearchMusic(int id){return musicDAO.findById(id);}



    public List<Music> SearchTitle(String keywords) {
        return musicDAO.findAllByTitleLike( '%'+ keywords + '%');
    }

    public void addOrUpdateMusic(Music music) {
        musicDAO.save(music);
    }

    public Music DelMusic(int id) { return musicDAO.deleteById(id);}
}
