package de.futurecompany.image.infrastructure.restapi;

import de.futurecompany.image.application.commandservices.ArticleImageCommandService;
import de.futurecompany.image.domain.ArticleImage;
import de.futurecompany.image.domain.ArticleImageId;
import de.futurecompany.image.infrastructure.repository.ArticleImageRepository;
import de.futurecompany.image.infrastructure.repository.ImageNewsArticleRepository;
import de.futurecompany.image.infrastructure.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@ExtendWith(value = {SpringExtension.class})
@WebFluxTest(controllers = {ArticleImageController.class})
class ArticleImageControllerTest {
    @MockBean
    ArticleImageCommandService articleImageCommandService;
    @MockBean
    ArticleImageRepository articleImageRepository;
    @MockBean
    ImageNewsArticleRepository imageNewsArticleRepository;
    @MockBean
    ImageRepository imageRepository;
    @Autowired
    private WebTestClient webClient;

    @Test
    public void should_return_articleImage_when_imageIsAssignable() {
        String imageId = UUID.randomUUID().toString();
        String articleId = UUID.randomUUID().toString();
        ArticleImage articleImage = new ArticleImage(imageId, articleId);
        ArticleImageId articleImageId = new ArticleImageId(articleImage.getImageId(), articleImage.getArticleId());
        Mockito.when(articleImageCommandService.assignImage(articleImage)).thenReturn(Mono.just(articleImage.toString()));
        Mockito.when(articleImageRepository.findById(articleImageId)).thenReturn(null);
        Mockito.when(articleImageRepository.save(articleImage)).thenReturn(articleImage);
        Mockito.when(imageNewsArticleRepository.findById(articleImage.getArticleId())).thenReturn(null);

        webClient.post()
                .uri("/assignImage/", new Object[0])
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(articleImage), ArticleImage.class)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

}