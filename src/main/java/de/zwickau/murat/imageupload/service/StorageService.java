package de.zwickau.murat.imageupload.service;

import de.zwickau.murat.imageupload.model.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    Resource loadAsResource(String filename);

    Image store(MultipartFile file);

    String getFileName(MultipartFile file);
}
