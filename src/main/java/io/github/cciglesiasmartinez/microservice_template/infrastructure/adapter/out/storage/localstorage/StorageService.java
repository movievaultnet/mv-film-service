package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.storage.localstorage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    // TODO: Add file hashing (MD5?) check. Might be needed to include in picture.
    void save(String editionSlug, String fileName, MultipartFile file);
    void delete(String editionSlug, String fileName);
    String getFileExtension(MultipartFile file);

}
