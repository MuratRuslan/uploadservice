package de.zwickau.murat.imageupload.repository;

import de.zwickau.murat.imageupload.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByUrl(String url);
}
