package com.sky.utils;

import com.sky.exception.BaseException;
import com.sky.properties.MinIOConfig;
import com.sky.properties.MinIOConfigProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.net.InetAddress;

@EnableConfigurationProperties(MinIOConfigProperties.class)
@Import(MinIOConfig.class)
@Service
public class MinioUtil {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinIOConfigProperties minIOConfigProperties;

    private final static String separator = "/";

    public String builderFilePath(String dirPath, String filename)
    {
        StringBuilder stringBuilder = new StringBuilder(50);
        if (!StringUtils.isEmpty(dirPath))
        {
            stringBuilder.append(dirPath).append(separator);
        }
        stringBuilder.append(filename);
        return stringBuilder.toString();
    }

    public String uploadImgFile(String prefix, String filename, InputStream inputStream)
    {
        String filePath = builderFilePath(prefix, filename);
        try
        {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object(filePath)
                    .contentType("image/jpg")
                    .bucket(minIOConfigProperties.getBucket()).stream(inputStream, inputStream.available(), -1)
                    .build();
            minioClient.putObject(putObjectArgs);
            String readPath = minIOConfigProperties.getReadPath();
            StringBuilder urlPath = new StringBuilder(readPath);
          //  urlPath.append(readPath.substring(readPath.lastIndexOf(":")));
            urlPath.append(separator + minIOConfigProperties.getBucket());
            urlPath.append(separator);
            urlPath.append(filePath);
            return urlPath.toString();
        }
        catch (Exception ex)
        {
            throw new BaseException("上传文件失败");
        }
    }


}
