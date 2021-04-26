package de.futurecompany.image.infrastructure.restapi;

import de.futurecompany.image.application.commandservices.ArticleImageCommandService;
import de.futurecompany.image.domain.ArticleImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
public class ArticleImageController {

    @Autowired
    ArticleImageCommandService articleImageCommandService;

    @PostMapping("/assignImage/")
    public Mono<ServerResponse> assignImage(@RequestBody ArticleImage articleImage) {
        try {
            return ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromPublisher(articleImageCommandService.assignImage(articleImage), String.class));
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.badRequest().contentType(MediaType.APPLICATION_JSON).bodyValue(e.getMessage());
        }
    }
}
