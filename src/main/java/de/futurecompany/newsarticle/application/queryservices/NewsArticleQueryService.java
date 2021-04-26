package de.futurecompany.newsarticle.application.queryservices;

import de.futurecompany.newsarticle.domain.ArticleAuthor;
import de.futurecompany.newsarticle.domain.ArticleView;
import de.futurecompany.newsarticle.domain.NewsArticle;
import de.futurecompany.newsarticle.infrastructure.repository.NewsArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NewsArticleQueryService {

    @Autowired
    NewsArticleRepository newsArticleRepository;

    /**
     * Fetch an {@link ArticleView} by id
     * @param id is {@link String}
     * @return a {@link Mono<ArticleView>}
     */
    public Mono<ArticleView> fetchArticle(String id) {
        var result = newsArticleRepository.findById(id).map(this::map);
        return Mono.justOrEmpty(result);
    }

    /**
     * Find all Articles
     * @return a {@link Flux<ArticleView>}
     */
    public Flux<ArticleView> listArticles() {
        Iterable<NewsArticle> list = newsArticleRepository.findAll();
        List<ArticleView> articleViewList = StreamSupport.stream(list.spliterator(), false)
                .map(this::map).collect(Collectors.toList());
        return Flux.fromIterable(articleViewList);
    }

    private ArticleView map(NewsArticle article) {
        return new ArticleView(
                article.getArticleId(),
                article.getTitle(),
                article.getText(),
                article.getAuthors().stream().map(ArticleAuthor::getAuthorName).collect(Collectors.toList())
        );
    }

}
