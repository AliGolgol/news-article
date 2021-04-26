package de.futurecompany.image.application.commandservices;

import de.futurecompany.image.domain.Image;
import de.futurecompany.image.infrastructure.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ImageCommandService {

    @Autowired
    private ImageRepository repository;

    /**
     * Get an Image object from RestAPI to persist
     * @param image is a {@link Image}
     * @return is a {@link Mono<Image>}
     */
    public Mono<Image> save(Image image) {
        String id = UUID.randomUUID().toString();
        image.setId(id);
        if (!isValid(image)){
            try {
                throw new Exception("This image is not acceptable");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Mono.justOrEmpty(repository.save(image));
    }

    private boolean isValid(Image image) {
        if (!image.getImageAuthor().isEmpty()
                || !image.getCaption().isEmpty()
                || !image.getUrl().isEmpty()
                || image.getPrice() > 0) {
            return true;
        }
        return false;
    }
}
