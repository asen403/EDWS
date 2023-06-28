package com.whs.edws.controller;

import com.whs.edws.common.ApiResponse;
import com.whs.edws.service.MinioService;
import com.whs.edws.utils.FileTypeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    private final MinioService minioService;

    @Value("${minio.bucket-name}")
    private  String bucketName;

    public FileController(MinioService minioService) {
        this.minioService = minioService;
    }

    @PostMapping("/upload")
    public ApiResponse<String> uploadFile(MultipartFile file){
        String fileType = FileTypeUtils.getFileType(file);
        if(fileType != null){
            return ApiResponse.success(minioService.putObject(file, bucketName, fileType));
        }
        return ApiResponse.fail("不支持的文件格式");
    }

}
