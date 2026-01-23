package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.storage.localstorage;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class StorageServiceImpl implements StorageService {

    private final Path root = Paths.get("static", "images");

    // TODO: Add file hashing (MD5?) check. Might be needed to include in picture.
    @Override
    public void  save(String editionSlug, String fileName, MultipartFile file) {
        try {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            Path dirPath = root.resolve(editionSlug);
            Files.createDirectories(dirPath);
            Path target = dirPath.resolve(fileName + "." + extension);
            Files.copy(file.getInputStream(), target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String editionSlug, String fileName) {
        try {
            Path dirPath = root.resolve(editionSlug);
            Path target = dirPath.resolve(fileName);
            Files.delete(target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getFileExtension(MultipartFile file) {
        return FilenameUtils.getExtension(file.getOriginalFilename());
    }

}
