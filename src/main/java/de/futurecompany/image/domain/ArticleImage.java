package de.futurecompany.image.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ArticleImageId.class)
public class ArticleImage {
    @Id
    private String imageId;
    @Id
    private String articleId;
}
