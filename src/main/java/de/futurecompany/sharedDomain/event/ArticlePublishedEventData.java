package de.futurecompany.sharedDomain.event;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ArticlePublishedEventData {
    private String articleId;

    ArticlePublishedEventData(final String articleId) {
        this.articleId = articleId;
    }
}
