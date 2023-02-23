package com.hzy.service;

import com.hzy.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @title: UploadService
 * @Author zxwyhzy
 * @Date: 2023/2/23 16:58
 * @Version 1.0
 */
public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
