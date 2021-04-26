package de.futurecompany.newsarticle.infrastructure.broker;

import de.futurecompany.accounting.application.commandservices.AccountArticleCreatedCommandService;
import de.futurecompany.image.application.commandservices.ImageArticleCreatedCommandService;
import de.futurecompany.publishing.application.commandservice.PublishingNewsArticleCommandService;
import de.futurecompany.sharedDomain.event.NewsArticleCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsArticleEventSource {

    @Autowired
    PublishingNewsArticleCommandService publishingCommand;
    @Autowired
    ImageArticleCreatedCommandService imageArticleCreatedCommandService;
    @Autowired
    AccountArticleCreatedCommandService accountArticleCreatedCommandService;

    /**
     * Publish an event to Publishing, Image and Accounting components
     * @param event is a {@link NewsArticleCreatedEvent}
     */
    public void publishArticle(NewsArticleCreatedEvent event){
        publishingCommand.createNewsArticle(event.getNewsArticleCreatedEventData());
        imageArticleCreatedCommandService.createNewsArticle(event.getNewsArticleCreatedEventData());
        accountArticleCreatedCommandService.createNewsArticle(event.getNewsArticleCreatedEventData());
    }
}
