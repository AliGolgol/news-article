package de.futurecompany.image.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ImageArticleAuthor {
    @Id
    private String id;
    private String authorName;
}
