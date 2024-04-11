package com.api.book.bootrestbook.controllers;

import com.api.book.bootrestbook.helper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
public class FileUploadController {
    @Autowired
    private FileUploadHelper fileUploadHelper;
    @PostMapping("upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file)
    {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("request must contain file");
            }
            if (!Objects.equals(file.getContentType(), "image/jpeg")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("request must contain images/jpeg");
            }
            boolean f= fileUploadHelper.uploadFile(file);
            if(f)
            {
                return ResponseEntity.ok("uploaded");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("went wrong");
    }
}
