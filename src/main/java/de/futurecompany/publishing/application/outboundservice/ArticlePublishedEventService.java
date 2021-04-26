package de.futurecompany.publishing.application.outboundservice;

import de.futurecompany.publishing.infrastructure.broker.PublishEventSource;
import de.futurecompany.sharedDomain.event.ArticlePublishedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ArticlePublishedEventService {

    @Autowired
    PublishEventSource eventSource;

    /**
     * It listens to an event which is raise as an article is published
     * @param event is a {@link ArticlePublishedEvent}
     */
    @EventListener
    public void handleArticlePublishedEvent(ArticlePublishedEvent event){
        eventSource.publishArticle(event);
    }
}
