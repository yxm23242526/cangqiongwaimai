package com.sky.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CommonService {
    String upload(MultipartFile file);
}
