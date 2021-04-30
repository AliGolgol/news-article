package de.futurecompany.newsarticle.domain;


import de.futurecompany.newsarticle.domain.commands.SaveNewsArticleCommand;
import de.futurecompany.sharedDomain.event.NewsArticleCreatedEvent;
import de.futurecompany.sharedDomain.event.NewsArticleCreatedEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NewsArticleAggregate {

    private String id;
    private NewsArticle newsArticle;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    /**
     * Create a NewsArticle and then raise events to Publishing, Image, Accounting components
     *
     * @param articleCommand is a {@link SaveNewsArticleCommand}
     * @return a {@link NewsArticle}
     */
    public NewsArticle createNewsArticle(SaveNewsArticleCommand articleCommand) {
        id = UUID.randomUUID().toString();
        newsArticle = new NewsArticle(id
                , articleCommand.getTitle()
                , articleCommand.getText()
                , articleCommand.getAuthors());

        eventPublisher.publishEvent(new NewsArticleCreatedEvent(
                NewsArticleCreatedEventData
                        .builder()
                        .articleId(newsArticle.getArticleId())
                        .authors(newsArticle.getAuthors())
                        .title(newsArticle.getTitle()).build()));

        return newsArticle;
    }
}
