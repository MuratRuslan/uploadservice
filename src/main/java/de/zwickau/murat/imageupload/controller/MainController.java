package de.zwickau.murat.imageupload.controller;

import de.zwickau.murat.imageupload.model.Image;
import de.zwickau.murat.imageupload.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class MainController {

    @Autowired
    private StorageService storageService;


    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        Image image = storageService.store(file);
        return image.getUrl();
    }


    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
