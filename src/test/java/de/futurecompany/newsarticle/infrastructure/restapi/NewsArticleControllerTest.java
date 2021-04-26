package de.futurecompany.newsarticle.infrastructure.restapi;

import de.futurecompany.newsarticle.application.commandservices.NewsArticleCommandService;
import de.futurecompany.newsarticle.application.queryservices.NewsArticleQueryService;
import de.futurecompany.newsarticle.domain.ArticleView;
import de.futurecompany.newsarticle.domain.commands.SaveNewsArticleCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(value={SpringExtension.class})
@WebFluxTest(controllers={NewsArticleController.class})
class NewsArticleControllerTest {
    @MockBean
    NewsArticleQueryService articleService;
    @MockBean
    NewsArticleCommandService commandService;
    @Autowired
    private WebTestClient webClient;

    NewsArticleControllerTest() {
    }

    @Test
    public void should_return_allArticle_when_thereAre() {
        String id = UUID.randomUUID().toString();
        List list = Stream.of(ArticleView.builder().articleId(id).build()).collect(Collectors.toList());
        Flux flux = Flux.fromIterable(list);
        Mockito.when((Object)this.articleService.listArticles()).thenReturn((Object)flux);
        this.webClient.get().uri("/articles/", new Object[0]).accept(new MediaType[]{MediaType.APPLICATION_JSON}).exchange().expectStatus().isOk().expectBodyList(ArticleView.class).consumeWith(response -> Assertions.assertEquals((Object)id, (Object)((ArticleView)((List)response.getResponseBody()).get(0)).getArticleId()));
    }

    @Test
    public void should_return_anArticle_when_thereIs() {
        String id = UUID.randomUUID().toString();
        Mono mono = Mono.just((Object)ArticleView.builder().articleId(id).build());
        Mockito.when((Object)this.articleService.fetchArticle(id)).thenReturn((Object)mono);
        this.webClient.get().uri("/articles/" + id, new Object[0]).accept(new MediaType[]{MediaType.APPLICATION_JSON}).exchange().expectStatus().isOk().expectBodyList(ArticleView.class).consumeWith(response -> Assertions.assertEquals((Object)id, (Object)((ArticleView)((List)response.getResponseBody()).get(0)).getArticleId()));
    }

    @Test
    public void should_create_anNewsArticle() {
        String id = UUID.randomUUID().toString();
        SaveNewsArticleCommand command = SaveNewsArticleCommand.builder().title("title").build();
        Mono mono = Mono.just((Object)ArticleView.builder().title("title").build());
        Mockito.when((Object)this.commandService.createNewsArticle(command)).thenReturn((Object)mono);
        ((WebTestClient.RequestBodySpec)((WebTestClient.RequestBodySpec)this.webClient.post().uri("/create", new Object[0])).accept(new MediaType[]{MediaType.APPLICATION_JSON})).exchange().expectStatus().isOk().expectBodyList(ArticleView.class).consumeWith(response -> Assertions.assertEquals((Object)"title", (Object)((ArticleView)((List)response.getResponseBody()).get(0)).getTitle()));
    }
}