package de.futurecompany.sharedDomain.event;

public class ArticlePublishedEvent {
    private ArticlePublishedEventData articlePublishedEventData;

    public ArticlePublishedEvent(final ArticlePublishedEventData articlePublishedEventData) {
        this.articlePublishedEventData = articlePublishedEventData;
    }

    public ArticlePublishedEventData getArticlePublishedEventData() {
        return this.articlePublishedEventData;
    }
}
