package com.evan.wj.controller;


import com.evan.wj.pojo.PhotoWidth;
import com.evan.wj.service.PhotoWidthService;

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
public class PhotoWidthController {
    @Autowired
    PhotoWidthService photoWidthService;

    @GetMapping("/api/photoWidths")
    public List<PhotoWidth> list() throws Exception {
        return photoWidthService.list();
    }

    @GetMapping("/api/photoWidthPage/{size}/{page}")
    public Page listPage(@PathVariable("size") int size, @PathVariable("page") int page) throws Exception {
        return photoWidthService.listPage(page - 1, size);
    }

    @PostMapping("/api/addPhotoWidth")
    public PhotoWidth addOrUpdatePhotoWidth(@RequestBody PhotoWidth photoWidth) throws Exception {
        //System.out.println(room.getRoom_rows());
        photoWidthService.addOrUpdatePhotoWidth(photoWidth);
        return photoWidth;
    }

    @PostMapping("/api/deletePhotoWidth")
    public void deleteMusic(@RequestBody PhotoWidth photoWidth) throws Exception {
        photoWidthService.DelPhotoWidth(photoWidth.getId());
    }

    @GetMapping("/api/photoWidth/{id}")
    public PhotoWidth listByPhotoWidth(@PathVariable("id") int id) throws Exception {
        if (0 != id) {
            return photoWidthService.SearchPhotoWidth(id);
        } else {
            return null;
        }
    }
    @GetMapping("/api/authorPhotoWidth/{author}")
    public List<PhotoWidth> listByAuthor(@PathVariable("author") String author) throws Exception {

        return photoWidthService.SearchAuthor(author);

    }


    //上传视频图片
    @CrossOrigin
    @PostMapping("api/photoWidthFile")
    public String musicImgUpload(MultipartFile file) throws Exception {
//      String folder = "D:/workspace/img";

        String folder = "D:/projects/blogFile/photoWidth";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, file.getOriginalFilename());
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:6660/api/file/photoWidth/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


}

