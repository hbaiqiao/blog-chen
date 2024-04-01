package com.evan.wj.controller;


import com.evan.wj.pojo.Vlog;
import com.evan.wj.service.VlogService;

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
public class VlogController {
    @Autowired
    VlogService vlogService;

    @GetMapping("/api/vlogs")
    public List<Vlog> list() throws Exception {
        return vlogService.list();
    }

    @GetMapping("/api/vlogPage/{size}/{page}")
    public Page listPage(@PathVariable("size") int size, @PathVariable("page") int page) throws Exception {
        return vlogService.listPage(page - 1, size);
    }

    @PostMapping("/api/addVlog")
    public Vlog addOrUpdateVlog(@RequestBody Vlog vlog) throws Exception {
        //System.out.println(room.getRoom_rows());
        vlogService.addOrUpdateVlog(vlog);
        return vlog;
    }

    @PostMapping("/api/deleteVlog")
    public void deleteMusic(@RequestBody Vlog vlog) throws Exception {
        vlogService.DelVlog(vlog.getId());
    }

    @GetMapping("/api/vlog/{id}")
    public Vlog listByMusic(@PathVariable("id") int id) throws Exception {
        if (0 != id) {
            return vlogService.SearchVlog(id);
        } else {
            return null;
        }
    }
    @GetMapping("/api/authorVlog/{author}")
    public List<Vlog> listByAuthor(@PathVariable("author") String author) throws Exception {

        return vlogService.SearchAuthor(author);

    }

    @CrossOrigin
    @GetMapping("/api/searchVlog")
    public List<Vlog> searchResult(@RequestParam("keywords") String keywords) {
        System.out.println(keywords);
        // 关键词为空时查询出所有
        if ("".equals(keywords)) {
            return vlogService.list();
        } else {
            return vlogService.SearchTitle(keywords);
        }
    }

    //上传视频
    @CrossOrigin
    @PostMapping("api/vlogFile")
    public String vlogUpload(MultipartFile file) throws Exception {
//      String folder = "D:/workspace/img";

        String folder = "D:/projects/blogFile/vlog";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, file.getOriginalFilename());
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:6660/api/file/vlog/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    //上传视频图片
    @CrossOrigin
    @PostMapping("api/vlogImgFile")
    public String musicImgUpload(MultipartFile file) throws Exception {
//      String folder = "D:/workspace/img";

        String folder = "D:/projects/blogFile/vlogImg";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, file.getOriginalFilename());
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:6660/api/file/vlogImg/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


}

