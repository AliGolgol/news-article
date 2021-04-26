package de.futurecompany.newsarticle.application.outboundservices;

import de.futurecompany.newsarticle.infrastructure.broker.NewsArticleEventSource;
import de.futurecompany.sharedDomain.event.NewsArticleCreatedEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class NewsArticleEventPublisherService {

    @Autowired
    NewsArticleEventSource eventSource;


    /**
     * It listen to a created article which is raised by NewsArticle component
     * @param event is a {@link NewsArticleCreatedEvent}
     */
    @EventListener
    public void handleNewsArticleCreatedEvent(NewsArticleCreatedEvent event){
        eventSource.publishArticle(event);
        System.out.println(event);
    }
}
