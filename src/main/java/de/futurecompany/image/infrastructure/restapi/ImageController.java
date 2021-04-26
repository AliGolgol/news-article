package de.futurecompany.image.infrastructure.restapi;

import de.futurecompany.image.application.commandservices.ImageCommandService;
import de.futurecompany.image.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ImageController {

    @Autowired
    ImageCommandService imageCommandService;

    @PostMapping("/upload/")
    public Mono<Image> upload(@RequestBody Image image){
        return imageCommandService.save(image);
    }
}
