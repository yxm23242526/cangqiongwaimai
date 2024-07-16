package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/common")
public class CommonController {
    @Autowired
    private CommonService commonService;

    @PostMapping("/upload")
    private Result<String> upload(MultipartFile file){
        String fileUrl = commonService.upload(file);
        return Result.success(fileUrl);
    }
}
