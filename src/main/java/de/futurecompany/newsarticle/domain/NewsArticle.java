package de.futurecompany.newsarticle.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsArticle {

    @Id
    private String articleId;

    private String title;

    private String text;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ArticleAuthor> authors;

}
