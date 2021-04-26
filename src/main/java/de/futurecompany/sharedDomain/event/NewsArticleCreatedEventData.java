package de.futurecompany.sharedDomain.event;

import de.futurecompany.newsarticle.domain.ArticleAuthor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class NewsArticleCreatedEventData {
    private String articleId;
    private String title;
    private List<ArticleAuthor> authors;
}
