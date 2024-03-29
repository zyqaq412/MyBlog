package com.hzy.controller;

import com.hzy.annotion.SystemLog;
import com.hzy.domain.ResponseResult;
import com.hzy.service.FileService;
import com.hzy.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @title: UploadController
 * @Author zxwyhzy
 * @Date: 2023/2/23 16:57
 * @Version 1.0
 */
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;
    @Autowired
    private FileService fileService;
    /**
     * 上传头像
     * @param img
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img){
        // return uploadService.uploadImg(multipartFile);
        return fileService.uploadImg(img);
    }
}
