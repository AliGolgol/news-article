package de.futurecompany.image.application.commandservices;

import com.google.gson.Gson;
import de.futurecompany.image.domain.ImageNewsArticle;
import de.futurecompany.image.infrastructure.repository.ImageNewsArticleRepository;
import de.futurecompany.sharedDomain.event.NewsArticleCreatedEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageArticleCreatedCommandService {

    @Autowired
    ImageNewsArticleRepository repository;

    /**
     * Get an event from NewsArticle component which is created
     * @param eventData is a {@link NewsArticleCreatedEventData}
     */
    public void createNewsArticle(NewsArticleCreatedEventData eventData){
        Gson gson = new Gson();
        try {
            ImageNewsArticle article = gson.fromJson(gson.toJson(eventData), ImageNewsArticle.class);
            repository.save(article);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
