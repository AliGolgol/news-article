package de.futurecompany.publishing.infrastructure.broker;

import de.futurecompany.accounting.application.commandservices.ArticlePublishedCommandService;
import de.futurecompany.sharedDomain.event.ArticlePublishedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishEventSource {

    @Autowired
    ArticlePublishedCommandService articlePublishedCommandService;

    /**
     * Publish an event to Account component
     * @param event is a {@link ArticlePublishedEvent}
     */
    public void publishArticle(ArticlePublishedEvent event){
        articlePublishedCommandService.publishArticle(event.getArticlePublishedEventData());
    }
}
