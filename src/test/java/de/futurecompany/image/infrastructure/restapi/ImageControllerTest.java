package de.futurecompany.image.infrastructure.restapi;

import de.futurecompany.image.application.commandservices.ImageCommandService;
import de.futurecompany.image.domain.Image;
import org.hamcrest.Matchers;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(value={SpringExtension.class})
@WebFluxTest(controllers={ImageController.class})
class ImageControllerTest {
    @MockBean
    ImageCommandService imageCommandService;
    @Autowired
    private WebTestClient webClient;

    @Test
    public void should_returnOkStatus_when_imageIsPersisted() {
        Image image = Image.builder().imageAuthor("alex").caption("AI article").price(1200).url("rul").build();
        Mockito.when(this.imageCommandService.save(image)).thenReturn(Mono.just(image));

        webClient.post()
                .uri("/upload/", new Object[0])
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(image), Image.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Image.class)
                .value(employee1 -> image.getCaption(), Matchers.equalTo("AI article"));
    }

}