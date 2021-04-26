package de.futurecompany.publishing.infrastructure.restapi;

import de.futurecompany.publishing.application.commandservice.PublishingNewsArticleCommandService;
import de.futurecompany.publishing.application.queryservice.PublicationService;
import de.futurecompany.publishing.domain.PublishingArticle;
import de.futurecompany.publishing.domain.PublishingView;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(value={SpringExtension.class})
@WebFluxTest(controllers={PublishingController.class})
class PublishingControllerTest {
    @MockBean
    PublicationService service;
    @MockBean
    PublishingNewsArticleCommandService publishingNewsArticleCommandService;
    @Autowired
    private WebTestClient webClient;

    PublishingControllerTest() {
    }

    @Test
    public void should_return_publishIsTrue_whe_isPublishProvided() {
        List list = Stream.of(new PublishingView[]{PublishingView.builder().published(true).build(), PublishingView.builder().published(true).build()}).collect(Collectors.toList());
        Flux flux = Flux.fromIterable(list);
        Mockito.when((Object)this.service.getAll()).thenReturn((Object)flux);
        this.webClient.get().uri("/publications/", new Object[0]).accept(new MediaType[]{MediaType.APPLICATION_JSON}).exchange().expectStatus().isOk().expectBodyList(PublishingView.class).consumeWith(response -> Assertions.assertEquals((Object)true, (Object)((PublishingView)((List)response.getResponseBody()).get(0)).isPublished()));
    }

    @Test
    public void should_return_publishArticle_whe_thereIs() {
        String id = UUID.randomUUID().toString();
        Mono mono = Mono.just((Object) PublishingArticle.builder().articleId(id).build());
        Mockito.when((Object)this.service.getById(id)).thenReturn((Object)mono);
        this.webClient.get().uri("/publications/" + id, new Object[0]).accept(new MediaType[]{MediaType.APPLICATION_JSON}).exchange().expectStatus().isOk().expectBodyList(PublishingArticle.class).consumeWith(response -> Assertions.assertEquals((Object)id, (Object)((PublishingArticle)((List)response.getResponseBody()).get(0)).getArticleId()));
    }

    @Test
    public void should_update_publishArticle_whe_thereIs() {
        String id = UUID.randomUUID().toString();
        Mono mono = Mono.just((Object)PublishingView.builder().published(true).build());
        Mockito.when((Object)this.publishingNewsArticleCommandService.publish(id)).thenReturn((Object)mono);
        ((WebTestClient.RequestBodySpec)((WebTestClient.RequestBodySpec)this.webClient.put().uri("/publications/" + id, new Object[0])).accept(new MediaType[]{MediaType.APPLICATION_JSON})).exchange().expectStatus().isOk().expectBodyList(PublishingView.class).consumeWith(response -> Assertions.assertEquals((Object)true, (Object)((PublishingView)((List)response.getResponseBody()).get(0)).isPublished()));
    }
}