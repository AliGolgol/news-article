package de.futurecompany.publishing.application.commandservice;

import com.google.gson.Gson;
import de.futurecompany.publishing.domain.PublishingArticle;
import de.futurecompany.publishing.domain.PublishingAuthor;
import de.futurecompany.publishing.domain.PublishingView;
import de.futurecompany.publishing.infrastructure.repository.PublishingNewsArticleRepository;
import de.futurecompany.sharedDomain.event.ArticlePublishedEvent;
import de.futurecompany.sharedDomain.event.ArticlePublishedEventData;
import de.futurecompany.sharedDomain.event.NewsArticleCreatedEventData;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
//@RequiredArgsConstructor
public class PublishingNewsArticleCommandService {

    @Autowired
    private PublishingNewsArticleRepository publishingNewsArticleRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    /**
     * Subscribe to {@link NewsArticleCreatedEventData} which is published by NewsArticle component to persist it
     * @param eventData is a {@link NewsArticleCreatedEventData}
     */
    public void createNewsArticle(NewsArticleCreatedEventData eventData) {
        Gson gson = new Gson();
        try {
            PublishingArticle article = gson.fromJson(gson.toJson(eventData), PublishingArticle.class);
            publishingNewsArticleRepository.save(article);
        } catch (Exception e) {
            e.getStackTrace();
        }
        System.out.println("Publisher get event data");
    }

    /**
     * Mark the Article as Published to customer
     * @param id is a {@link String}
     * @return a {@link Mono<PublishingView>}
     */
    public Mono<PublishingView> publish(String id) {
        Optional<PublishingArticle> publishingArticle = publishingNewsArticleRepository.findById(id);
        if (!publishingArticle.isPresent()) {
            try {
                throw new NotFoundException("The article not found");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        publishingArticle.get().setPublished(true);
        PublishingArticle save = publishingNewsArticleRepository.save(publishingArticle.get());

        eventPublisher.publishEvent(new ArticlePublishedEvent(ArticlePublishedEventData.builder()
        .articleId(save.getArticleId()).build()));

        return Mono.justOrEmpty(map(save));
    }

    private PublishingView map(PublishingArticle publishingArticle) {
        return PublishingView.builder()
                .articleId(publishingArticle.getArticleId())
                .title(publishingArticle.getTitle())
                .published(publishingArticle.isPublished())
                .authors(publishingArticle.getAuthors().stream().map(PublishingAuthor::getAuthorName).collect(Collectors.toList())).build();

    }
}
