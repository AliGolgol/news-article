package de.futurecompany.image.infrastructure.restapi;

import de.futurecompany.image.application.commandservices.ArticleImageCommandService;
import de.futurecompany.image.domain.ArticleImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ArticleImageController {

    @Autowired
    ArticleImageCommandService articleImageCommandService;

    @PostMapping("/assignImage/")
    public Mono<String> assignImage(@RequestBody ArticleImage articleImage) {
        return articleImageCommandService.assignImage(articleImage);
    }
}
