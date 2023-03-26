package com.hzy.service;

import com.hzy.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @title: FileService
 * @Author zxwyhzy
 * @Date: 2023/3/25 23:31
 * @Version 1.0
 */
public interface FileService {

    ResponseResult uploadImg(MultipartFile img);
}
