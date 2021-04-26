package de.futurecompany.publishing.infrastructure.restapi;

import de.futurecompany.publishing.application.commandservice.PublishingNewsArticleCommandService;
import de.futurecompany.publishing.application.queryservice.PublicationService;
import de.futurecompany.publishing.domain.PublishingArticle;
import de.futurecompany.publishing.domain.PublishingView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PublishingController {

    @Autowired
    PublicationService service;
    @Autowired
    PublishingNewsArticleCommandService publishingNewsArticleCommandService;

    @GetMapping("/publications/{id}")
    public Mono<PublishingArticle> get(@PathVariable String id){
        return service.getById(id);
    }

    @GetMapping("/publications/")
    public Flux<PublishingView> get(){
        return service.getAll();
    }

    @PutMapping("/publications/{id}")
    public Mono<PublishingView> update(@PathVariable String id){
        return publishingNewsArticleCommandService.publish(id);
    }
}
