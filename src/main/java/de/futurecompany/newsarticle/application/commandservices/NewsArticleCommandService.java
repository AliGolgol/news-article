package de.futurecompany.newsarticle.application.commandservices;

import de.futurecompany.newsarticle.domain.ArticleAuthor;
import de.futurecompany.newsarticle.domain.ArticleView;
import de.futurecompany.newsarticle.domain.NewsArticle;
import de.futurecompany.newsarticle.domain.NewsArticleAggregate;
import de.futurecompany.newsarticle.domain.commands.SaveNewsArticleCommand;
import de.futurecompany.newsarticle.infrastructure.repository.NewsArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
public class NewsArticleCommandService {

    @Autowired
    NewsArticleRepository newsArticleRepository;
    @Autowired
    NewsArticleAggregate aggregate;

    /**
     * It gets a {@link SaveNewsArticleCommand} from RESTAPI to persist it
     * @param newsArticleCommand is a {@link SaveNewsArticleCommand}
     * @return a {@link Mono<ArticleView>}
     */
    public Mono<ArticleView> createNewsArticle(SaveNewsArticleCommand newsArticleCommand) {
        NewsArticle newsArticle = aggregate.createNewsArticle(newsArticleCommand);
        newsArticleRepository.save(newsArticle);
        return Mono.justOrEmpty(map(newsArticle));
    }

    private ArticleView map(NewsArticle article) {
        return new ArticleView(article.getArticleId(),
                article.getTitle(), article.getText(),
                article.getAuthors().stream().map(ArticleAuthor::getAuthorName).collect(Collectors.toList())
        );
    }
}
