package de.zwickau.murat.imageupload.service;

import de.zwickau.murat.imageupload.model.Image;
import de.zwickau.murat.imageupload.repository.ImageRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Service
public class DefaultStorageService implements StorageService {

    @Value("${image.directory}")
    private String imageDirectory;

    @Autowired
    private ImageRepository repository;

    @Value("${internet.address}")
    private String serverAddress;

    @PostConstruct
    @SneakyThrows
    public void init() {
        if (!Files.isDirectory(Paths.get(imageDirectory))) {
            Files.createDirectory(Paths.get(imageDirectory));
        }
    }

    @SneakyThrows
    @Override
    public Resource loadAsResource(String identifier) {
        String url = "http://" + serverAddress + "/files/" + identifier;
        Image image = repository.findByUrl(url);
        String path = image.getPath();
        Path file = Paths.get(path);
        Resource resource = new UrlResource(file.toUri());
        if (resource.exists() && resource.isReadable()) {
            return resource;
        }
        return null;
    }


    @Transactional
    @Override
    public Image store(MultipartFile file) {
        Image image = new Image();
        try (InputStream inputStream = file.getInputStream()) {
            Date now = new Date();
            String filename = now.getTime() + "_" + getFileName(file);
            Path rootLocation = Paths.get(imageDirectory);
            Files.copy(inputStream, rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);

            image = new Image(imageDirectory + "/" + filename,
                    generateLink(filename, now));
            repository.save(image);
            return image;
        } catch (Exception e) {
            return image;
        }
    }

    @Override
    public String getFileName(MultipartFile file) {
        return StringUtils.cleanPath(file.getOriginalFilename());
    }

    private String generateLink(String filename, Date date) {
        return "http://" + serverAddress + "/files/" +
                filename.hashCode() + "" + date.getTime();
    }
}
