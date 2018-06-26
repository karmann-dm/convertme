package com.karmanno.letmeconvert.repository;

import com.karmanno.letmeconvert.model.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {
    Optional<UploadedFile> findByName(String name);
}
