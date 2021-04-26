package de.futurecompany.sharedDomain.event;

public class NewsArticleCreatedEvent {
    private NewsArticleCreatedEventData newsArticleCreatedEventData;

    public NewsArticleCreatedEvent(NewsArticleCreatedEventData newsArticleCreatedEventData) {
        this.newsArticleCreatedEventData = newsArticleCreatedEventData;
    }

    public NewsArticleCreatedEventData getNewsArticleCreatedEventData() {
        return this.newsArticleCreatedEventData;
    }
}
