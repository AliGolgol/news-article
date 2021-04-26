package de.futurecompany.publishing.application.queryservice;

import de.futurecompany.publishing.domain.PublishingArticle;
import de.futurecompany.publishing.domain.PublishingAuthor;
import de.futurecompany.publishing.domain.PublishingView;
import de.futurecompany.publishing.infrastructure.repository.PublishingNewsArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PublicationService {

    @Autowired
    PublishingNewsArticleRepository repository;

    /**
     * Fetch an Article and its related Authors
     * @param id is a {@link String}
     * @return a {@link Mono<PublishingArticle>}
     */
    public Mono<PublishingArticle> getById(String id){
        Optional<PublishingArticle> publishingArticle = repository.findById(id);
        return Mono.just(publishingArticle.get());
    }

    /**
     * Find all articles
     * @return is a {@link Flux<PublishingView>}
     */
    public Flux<PublishingView> getAll(){
        Iterable<PublishingArticle> publishingArticle = repository.findAll();
        List<PublishingView> publishingArticleList = StreamSupport.stream(publishingArticle.spliterator(), false)
                .map(this::map).collect(Collectors.toList());

        return Flux.fromIterable(publishingArticleList);
    }

    private PublishingView map(PublishingArticle publishingArticle) {
        return PublishingView.builder()
        .articleId(publishingArticle.getArticleId())
                .title(publishingArticle.getTitle())
                .published(publishingArticle.isPublished())
                .authors(publishingArticle.getAuthors().stream().map(PublishingAuthor::getAuthorName).collect(Collectors.toList())).build();

    }
}
