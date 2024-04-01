package com.evan.wj.controller;


import com.evan.wj.pojo.Music;
import com.evan.wj.service.MusicService;

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
public class MusicController {
    @Autowired
    MusicService musicService;

    @GetMapping("/api/musics")
    public List<Music> list() throws Exception {
        return musicService.list();
    }

    @GetMapping("/api/musicPage/{size}/{page}")
    public Page listPage(@PathVariable("size") int size, @PathVariable("page") int page) throws Exception {
        return musicService.listPage(page - 1, size);
    }

    @PostMapping("/api/addMusic")
    public Music addOrUpdateMusic(@RequestBody Music music) throws Exception {
        //System.out.println(room.getRoom_rows());
        musicService.addOrUpdateMusic(music);
        return music;
    }

    @PostMapping("/api/deleteMusic")
    public void deleteMusic(@RequestBody Music music) throws Exception {
        musicService.DelMusic(music.getId());
    }

    @GetMapping("/api/music/{id}")
    public Music listByMusic(@PathVariable("id") int id) throws Exception {
        if (0 != id) {
            return musicService.SearchMusic(id);
        } else {
            return null;
        }
    }
    @GetMapping("/api/authorMusic/{author}")
    public List<Music> listByAuthor(@PathVariable("author") String author) throws Exception {

        return musicService.SearchAuthor(author);

    }

    @CrossOrigin
    @GetMapping("/api/searchMusic")
    public List<Music> searchResult(@RequestParam("keywords") String keywords) {
        System.out.println(keywords);
        // 关键词为空时查询出所有
        if ("".equals(keywords)) {
            return musicService.list();
        } else {
            return musicService.SearchTitle(keywords);
        }
    }

    //上传音乐
    @CrossOrigin
    @PostMapping("api/musicFile")
    public String musicUpload(MultipartFile file) throws Exception {

        String folder = "D:/projects/blogFile/music";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, file.getOriginalFilename());
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:6660/api/file/music/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    //上传音乐图片
    @CrossOrigin
    @PostMapping("api/musicImgFile")
    public String musicImgUpload(MultipartFile file) throws Exception {

        String folder = "D:/projects/blogFile/musicImg";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, file.getOriginalFilename());
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:6660/api/file/musicImg/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


}

