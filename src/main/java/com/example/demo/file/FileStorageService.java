package com.example.demo.file;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    @Value("${file.upload.dir}")
    private String baseUploadDir;

    public String storedFile(MultipartFile file, String prefix) throws IOException {
        // 업로드한 원래 이름
        String originalFilename = file.getOriginalFilename();
        // 확장자 추출
        String ext = originalFilename.substring(originalFilename.lastIndexOf('.'));
        // prefix + UUID + 확장자
        String storedFilename = prefix + UUID.randomUUID() + ext;

        Path savePath = Paths.get(baseUploadDir,"product-images").toAbsolutePath().normalize();
        Files.createDirectories(savePath);

        Path filePath = savePath.resolve(storedFilename);
        Files.copy(file.getInputStream(),filePath, StandardCopyOption.REPLACE_EXISTING);

        return storedFilename;

    }
}
