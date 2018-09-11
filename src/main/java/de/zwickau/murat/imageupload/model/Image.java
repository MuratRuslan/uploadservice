package de.zwickau.murat.imageupload.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "image")
public class Image {

    @Id
    private String id;
    private String path;
    private String url;

    public Image(){}

    public Image(String path, String url) {
        this.path = path;
        this.url = url;
    }
}
