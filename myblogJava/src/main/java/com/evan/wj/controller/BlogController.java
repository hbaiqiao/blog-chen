package com.evan.wj.controller;


import com.evan.wj.pojo.Blog;
import com.evan.wj.service.BlogService;

import com.evan.wj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@CrossOrigin
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/api/blogs")
    public List<Blog> list() throws Exception {
        return blogService.list();
    }

    @GetMapping("/api/blogsPage/{size}/{page}")
    public Page listPage(@PathVariable("size") int size, @PathVariable("page") int page) throws Exception {
        return blogService.listPage(page - 1, size);
    }

    @PostMapping("/api/addBlog")
    public Blog addOrUpdateBlog(@RequestBody Blog blog) throws Exception {
        //System.out.println(room.getRoom_rows());
        blogService.addOrUpdateBlog(blog);
        return blog;
    }

    @PostMapping("/api/deleteBlog")
    public void deleteBlog(@RequestBody Blog blog) throws Exception {
        blogService.DelBlog(blog.getId());
    }

    @GetMapping("/api/blog/{id}")
    public Blog listByBlog(@PathVariable("id") int id) throws Exception {
        if (0 != id) {
            return blogService.SearchBlog(id);
        } else {
            return null;
        }
    }
    @GetMapping("/api/authorBlog/{author}")
    public List<Blog> listByAuthor(@PathVariable("author") String author) throws Exception {

        return blogService.SearchAuthor(author);

    }

    @GetMapping("/api/typeBlog/{type}")
    public List<Blog> listByType(@PathVariable("type") String type) throws Exception {
        if (type!=null) {
            return blogService.SearchType(type);
        } else {
            return blogService.list();
        }
    }

    @PostMapping("/api/ContentBlog")
    public List<Blog> searchContentBlog(@RequestBody Blog request) {
        return blogService.SearchContent(request.getContent());
    }

    @CrossOrigin
    @GetMapping("/api/searchBlog")
    public List<Blog> searchResult(@RequestParam("keywords") String keywords) {
        System.out.println(keywords);
        // 关键词为空时查询出所有
        if ("".equals(keywords)) {
            return blogService.list();
        } else {
            return blogService.SearchContent(keywords);
        }
    }

    @CrossOrigin
    @PostMapping("api/imgBlog")
    public String coversUpload(MultipartFile file) throws Exception {
//      String folder = "D:/workspace/img";
        System.out.println(file);
        String folder = "D:/projects/blogFile/blog";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, file.getOriginalFilename());
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:6660/api/file/blog/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


}

