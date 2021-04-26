package de.futurecompany.newsarticle.infrastructure.restapi;

import de.futurecompany.newsarticle.application.commandservices.NewsArticleCommandService;
import de.futurecompany.newsarticle.application.queryservices.NewsArticleQueryService;
import de.futurecompany.newsarticle.domain.ArticleView;
import de.futurecompany.newsarticle.domain.commands.SaveNewsArticleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class NewsArticleController {

    @Autowired
    NewsArticleQueryService articleService;
    @Autowired
    NewsArticleCommandService commandService;

    @GetMapping("/articles/")
    public Flux<ArticleView> get() {
        return articleService.listArticles();

    }

    @GetMapping("/articles/{id}")
    public Mono<ArticleView> getById(@PathVariable String id) {
        Mono<ArticleView> result = articleService.fetchArticle(id);
        return result;
    }

    @PostMapping("/create")
    public Mono<ArticleView> create(@RequestBody SaveNewsArticleCommand command) {
        return commandService.createNewsArticle(command);
    }
}
