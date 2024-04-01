package com.evan.wj.controller;


import com.evan.wj.pojo.PhotoHeight;
import com.evan.wj.service.PhotoHeightService;

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
public class PhotoHeightController {
    @Autowired
    PhotoHeightService photoHeightService;

    @GetMapping("/api/photoHeights")
    public List<PhotoHeight> list() throws Exception {
        return photoHeightService.list();
    }

    @GetMapping("/api/photoHeightPage/{size}/{page}")
    public Page listPage(@PathVariable("size") int size, @PathVariable("page") int page) throws Exception {
        return photoHeightService.listPage(page - 1, size);
    }

    @PostMapping("/api/addPhotoHeight")
    public PhotoHeight addOrUpdatePhotoHeight(@RequestBody PhotoHeight photoHeight) throws Exception {
        //System.out.println(room.getRoom_rows());
        photoHeightService.addOrUpdatePhotoHeight(photoHeight);
        return photoHeight;
    }

    @PostMapping("/api/deletePhotoHeight")
    public void deleteMusic(@RequestBody PhotoHeight photoHeight) throws Exception {
        photoHeightService.DelPhotoWidth(photoHeight.getId());
    }

    @GetMapping("/api/photoHeight/{id}")
    public PhotoHeight listByPhotoHeight(@PathVariable("id") int id) throws Exception {
        if (0 != id) {
            return photoHeightService.SearchPhotoHeight(id);
        } else {
            return null;
        }
    }
    @GetMapping("/api/authorPhotoHeight/{author}")
    public List<PhotoHeight> listByAuthor(@PathVariable("author") String author) throws Exception {

        return photoHeightService.SearchAuthor(author);

    }


    //上传图片
    @CrossOrigin
    @PostMapping("api/photoHeightFile")
    public String musicImgUpload(MultipartFile file) throws Exception {


        String folder = "D:/projects/blogFile/photoHeight";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, file.getOriginalFilename());
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:6660/api/file/photoHeight/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


}

