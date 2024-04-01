package com.evan.wj.controller;

import com.evan.wj.pojo.Item;
import com.evan.wj.pojo.User;
import com.evan.wj.service.UserService;

import com.evan.wj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/api/users")
    public List<User> list() throws Exception {
        return userService.list();
    }

    @PostMapping("/api/adduser")
    public User addUser(@RequestBody User user) throws Exception {
        //System.out.println(room.getRoom_rows());
        userService.addOrUpdateUser(user);
        return user;
    }

    @GetMapping("/api/user/{id}")
    public User listByItem(@PathVariable("id") int id) throws Exception {
        if (0 != id) {
            return userService.getUser(id);
        } else {
            return null;
        }
    }

    //@GetMapping("/api/find")
    @PostMapping("/api/getUser")
    @CrossOrigin

    //@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "*",methods = {POST,GET,OPTIONS})
    //public List<Room> searchResult(@RequestParam("id") String id) {
    public User search(@RequestBody User request) {
        //response.setHeader("Access-Control-Allow-Origin", "*"); //
        //response.setHeader("Cache-Control","no-cache");       //
        //System.out.println(request.getId());
        return userService.Search(request.getNum());

    }

    @CrossOrigin
    @PostMapping("api/userImg")
    public String coversUpload(MultipartFile file) throws Exception {
//      String folder = "D:/workspace/img";

        String folder = "D:/projects/毕业设计/workplace/userImg";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8440/api/userImg/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}

