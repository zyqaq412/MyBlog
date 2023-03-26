package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.service.FileService;
import com.hzy.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @title: UploadController
 * @Author zxwyhzy
 * @Date: 2023/2/26 16:48
 * @Version 1.0
 */
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(@RequestParam("img") MultipartFile multipartFile) {
        // return uploadService.uploadImg(multipartFile);
         return fileService.uploadImg(multipartFile);

    }
}
