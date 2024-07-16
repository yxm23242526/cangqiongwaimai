package com.sky.service.impl;

import com.sky.service.CommonService;
import com.sky.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private MinioUtil minioUtil;

    @Override
    public String upload(MultipartFile file){
        String url = "";
        try{
            url = minioUtil.uploadImgFile("dish", String.valueOf(UUID.randomUUID()), file.getInputStream());
        }
        catch (Exception e){
            
        }
        return url;
    }
}
