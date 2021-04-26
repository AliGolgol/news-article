package de.futurecompany.image.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageNewsArticle {
    @Id
    private String articleId;
    private String title;
    private boolean published;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ImageArticleAuthor> authors;
}
