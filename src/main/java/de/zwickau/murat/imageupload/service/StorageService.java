package de.zwickau.murat.imageupload.service;

import de.zwickau.murat.imageupload.model.Image;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface StorageService {

    Resource loadAsResource(String filename);

    Image store(MultipartFile file);

    byte[] resizeImage(File image, float percent);

    byte[] resizeImage(File image, String size);

    String getFileName(MultipartFile file);
}
