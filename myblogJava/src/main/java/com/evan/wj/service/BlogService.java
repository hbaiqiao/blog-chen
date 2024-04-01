package com.evan.wj.service;

import com.evan.wj.dao.BlogDAO;

import com.evan.wj.pojo.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogDAO blogDAO;



    public List<Blog> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return blogDAO.findAll(sort);
//        return blogDAO.findAll();
    }
    public Page listPage(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return blogDAO.findAll(PageRequest.of(page, size, sort));

    }

    public List<Blog> SearchAuthor(String author) {
        return blogDAO.findByAuthor(author);
    }

    public Blog SearchBlog(int id){return blogDAO.findById(id);}

    public List<Blog> SearchType(String type) {return blogDAO.findByType(type); }

    public List<Blog> SearchContent(String keywords) {
        return blogDAO.findAllByContentLike( '%'+ keywords + '%');
    }

    public void addOrUpdateBlog(Blog blog) {
        blogDAO.save(blog);
    }

    public Blog DelBlog(int id) { return blogDAO.deleteById(id);}
}
