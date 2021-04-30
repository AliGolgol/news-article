package de.futurecompany.image.application.commandservices;

import de.futurecompany.image.domain.ArticleImage;
import de.futurecompany.image.domain.ArticleImageId;
import de.futurecompany.image.domain.Image;
import de.futurecompany.image.domain.ImageNewsArticle;
import de.futurecompany.errorhandling.FutureBusinessException;
import de.futurecompany.image.infrastructure.repository.ArticleImageRepository;
import de.futurecompany.image.infrastructure.repository.ImageNewsArticleRepository;
import de.futurecompany.image.infrastructure.repository.ImageRepository;
import de.futurecompany.sharedDomain.event.ImageAssignedEvent;
import de.futurecompany.sharedDomain.event.ImageAssignedEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ArticleImageCommandService {

    @Autowired
    private ArticleImageRepository articleImageRepository;
    @Autowired
    private ImageNewsArticleRepository imageNewsArticleRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * Assign an image to an article
     *
     * @param articleImage is a {@link ArticleImage}
     * @return a {@link Mono<String>}
     */
    public Mono<String> assignImage(ArticleImage articleImage) {
        ArticleImage result;
        Optional<ImageNewsArticle> imageNewsArticle = imageNewsArticleRepository.findById(articleImage.getArticleId());

        if (!imageNewsArticle.isPresent()) {
            throw new FutureBusinessException(HttpStatus.BAD_REQUEST, "There is not such this article!", null);
        } else {

            Optional<Image> image = imageRepository.findById(articleImage.getImageId());
            ArticleImageId articleImageId = new ArticleImageId(articleImage.getImageId(), articleImage.getArticleId());
            Optional<ArticleImage> byId = articleImageRepository.findById(articleImageId);
            if (!byId.isEmpty()) {
                throw new FutureBusinessException(HttpStatus.BAD_REQUEST, "Duplication may occur!", null);
            }
            result = articleImageRepository.save(articleImage);
            publisher.publishEvent(new ImageAssignedEvent(
                    ImageAssignedEventData.builder()
                            .imageUrl(image.get().getUrl())
                            .articleId(articleImage.getArticleId())
                            .Author(image.get().getImageAuthor())
                            .price(image.get().getPrice()).build()
            ));

            return Mono.justOrEmpty(result.toString());
        }
    }
}
