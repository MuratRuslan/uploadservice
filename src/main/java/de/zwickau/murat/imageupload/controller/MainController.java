package de.zwickau.murat.imageupload.controller;

import de.zwickau.murat.imageupload.model.Image;
import de.zwickau.murat.imageupload.service.StorageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


@RestController
public class MainController {

    @Autowired
    private StorageService storageService;


    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        Image image = storageService.store(file);
        return image.getUrl();
    }

    @SneakyThrows
    @GetMapping("/files/{filename}")
    public ResponseEntity getImage(@PathVariable String filename, @RequestParam(defaultValue = "") String size) {
        Resource file = storageService.loadAsResource(filename);
        byte[] bytes = storageService.resizeImage(file.getFile(), size);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,
                "image/jpeg").body(bytes);
    }

}
