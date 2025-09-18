package com.doctorbookingsystem.doctorbooking.service.impl;

import com.doctorbookingsystem.doctorbooking.service.interfaces.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageServiceImp implements FileStorageService {


    private static final String UPLOAD_DIR = "uploads";

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        file.transferTo(filePath.toFile());
        return filePath.toString();
    }
}
