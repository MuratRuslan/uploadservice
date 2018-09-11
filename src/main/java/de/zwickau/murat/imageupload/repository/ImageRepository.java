package de.zwickau.murat.imageupload.repository;

import de.zwickau.murat.imageupload.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image, Long> {
    Image findByUrl(String url);
}
