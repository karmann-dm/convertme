package com.karmanno.letmeconvert.service;

import com.karmanno.letmeconvert.model.UploadedFile;
import com.karmanno.letmeconvert.repository.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final static String STORAGE_PATH = "/src/main/java/resources/uploads";

    private ResourceLoader resourceLoader;

    @Override
    public void init() {

    }

    @Override
    public UploadedFile store(MultipartFile file) throws IOException {
        if(file.isEmpty())
            return null;
        Files.copy(file.getInputStream(), Paths.get("src/main/resources/uploads").resolve(file.getOriginalFilename()));
        UploadedFile uploadedFile = new UploadedFile();
        uploadedFile.setPathToFile("src/main/resources/uploads/" + file.getOriginalFilename());
        uploadedFile.setName(file.getOriginalFilename());
        return uploadedFile;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return Paths.get(filename);
    }

    @Override
    public Resource loadAsResource(String filename) throws MalformedURLException {
        Path file = Paths.get(filename);
        Resource resource = new UrlResource(file.toUri());
        if(resource.exists() || resource.isReadable())
            return resource;
        else
            throw new RuntimeException("Failed to load file " + filename + " as resource");
    }

    @Override
    public void deleteAll() {

    }
}
