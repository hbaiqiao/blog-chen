package com.evan.wj.controller;


import com.evan.wj.pojo.Item;
import com.evan.wj.service.ItemService;

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
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping("/api/items")
    public List<Item> list() throws Exception {
        return itemService.list();
    }

    @PostMapping("/api/addItem")
    public Item addOrUpdateItem(@RequestBody Item item) throws Exception {
        //System.out.println(room.getRoom_rows());
        itemService.addOrUpdateItem(item);
        return item;
    }

    @PostMapping("/api/item/delete")
    public void deleteItem(@RequestBody Item item) throws Exception {
        itemService.DelItem(item.getId());
    }
    //@PostMapping("/api/{id}/rooms")
   /*public List<Room> getById(@PathVariable("id") int id) throws Exception {
       //roomService.get(id);
       return roomService.getById(id);
   }*/
   @GetMapping("/api/item/{id}")
   public Item listByItem(@PathVariable("id") int id) throws Exception {
       if (0 != id) {
           return itemService.SearchItem(id);
       } else {
           return null;
       }
   }
    @GetMapping("/api/item/user/{uid}")
    public List<Item> listByUid(@PathVariable("uid") int uid) throws Exception {
        if (0 != uid) {
            return itemService.SearchUid(uid);
        } else {
            return null;
        }
    }

    @GetMapping("/api/item/topic/{topic}")
    public List<Item> listByTopic(@PathVariable("topic") String topic) throws Exception {
        if (topic!=null) {
            return itemService.SearchTopic(topic);
        } else {
            return itemService.list();
        }
    }


    @PostMapping("/api/findUserItem")
    //@CrossOrigin
    public Item searchUserItem(@RequestBody Item request) {
        return itemService.SearchUser(request.getUsername());
    }

    @PostMapping("/api/findInfoItem")
    public List<Item> searchInfoItem(@RequestBody Item request) {
        return itemService.SearchInfo(request.getInfo());
    }




    @CrossOrigin
    @GetMapping("/api/items/search")
    public List<Item> searchResult(@RequestParam("keywords") String keywords) {
        System.out.println(keywords);
        // 关键词为空时查询出所有书籍
        if ("".equals(keywords)) {
            return itemService.list();
        } else {
            return itemService.SearchInfo(keywords);
        }
    }

    @CrossOrigin
    @PostMapping("api/images")
    public String coversUpload(MultipartFile file) throws Exception {
//      String folder = "D:/workspace/img";

        String folder = "D:/projects/毕业设计/workplace/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8440/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


}

