package de.zwickau.murat.imageupload.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Image {

    @Id
    @GeneratedValue
    private Long id;
    private String path;
    private String url;

    public Image(){}

    public Image(String path, String url) {
        this.path = path;
        this.url = url;
    }
}
