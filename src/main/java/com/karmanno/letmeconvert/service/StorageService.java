package com.karmanno.letmeconvert.service;

import com.karmanno.letmeconvert.model.UploadedFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    void init();
    UploadedFile store(MultipartFile file) throws IOException;
    Stream<Path> loadAll();
    Path load(String filename);
    Resource loadAsResource(String filename) throws MalformedURLException;
    void deleteAll();
}
