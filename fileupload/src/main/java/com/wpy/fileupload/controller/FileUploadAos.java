package com.wpy.fileupload.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class FileUploadAos {
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败";
        }
        String originalFilename = file.getOriginalFilename() + "." + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        String fileName = file.getName() + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + file.getContentType();
        String filePath = "/home/upload/";
        File filePaths = new File(filePath);
        if (!filePaths.exists()) {
            try {
                boolean newFile = filePaths.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File fileResult = new File(filePath + originalFilename);
        try {
            file.transferTo(fileResult);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @GetMapping("/con")
    public String con() {
        return "ok";
    }

}
