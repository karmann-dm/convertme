package com.karmanno.letmeconvert.controller;

import com.karmanno.letmeconvert.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/files")
public class FileUploadController {
    @Autowired
    private StorageService storageService;
}
