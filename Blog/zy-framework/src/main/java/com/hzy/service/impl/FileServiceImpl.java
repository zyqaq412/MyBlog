package com.hzy.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.hzy.domain.ResponseResult;
import com.hzy.enums.AppHttpCodeEnum;
import com.hzy.exception.SystemException;
import com.hzy.service.FileService;
import com.hzy.utils.PathUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @title: FileServiceImpl
 * @Author zxwyhzy
 * @Date: 2023/3/25 23:32
 * @Version 1.0
 */
@Service
@Data
@ConfigurationProperties(prefix = "oss")
public class FileServiceImpl implements FileService {


    String endpoint ;
    String keyid ;
    String keysecret ;
    String bucketname ;
    @Override
    public ResponseResult uploadImg(MultipartFile img) {
        //判断文件类型
        //获取原始文件名
        String originalFilename = img.getOriginalFilename();
        //对原始文件名进行判断
        if(!originalFilename.endsWith(".png")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }

        //如果判断通过上传文件到OSS
        String filePath = PathUtils.generateFilePath(originalFilename);
        String url = upload(img,filePath);//  2099/2/3/wqeqeqe.png
        System.out.println(url);
        return ResponseResult.okResult(url);
    }
    public String upload(MultipartFile file, String filePath) {

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);
            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            // String fileName = file.getOriginalFilename();
            //生成随机唯一值，使用uuid，添加到文件名称里面，不会导致重名
            // String uuid = UUID.randomUUID().toString().replaceAll("-","");
            // fileName = uuid+fileName;
            //调用方法实现上传
            ossClient.putObject(bucketname, filePath, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //上传之后文件路径
            // https://yygh-cccwm.oss-cn-shenzhen.aliyuncs.com/01.jpg
            String url = "https://"+bucketname+"."+endpoint+"/"+filePath;
            //返回
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
