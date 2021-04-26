package de.futurecompany.accounting.application.commandservices;

import com.google.gson.Gson;
import de.futurecompany.accounting.domain.AccountingNewsArticle;
import de.futurecompany.accounting.infrastructure.repository.AccountingNewsArticleRepository;
import de.futurecompany.sharedDomain.event.NewsArticleCreatedEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountArticleCreatedCommandService {

    @Autowired
    AccountingNewsArticleRepository repository;

    /**
     * Create a NewsArticle
     * @param eventData is a {@link NewsArticleCreatedEventData}
     */
    public void createNewsArticle(NewsArticleCreatedEventData eventData){
        Gson gson = new Gson();
        try {
            AccountingNewsArticle article = gson.fromJson(gson.toJson(eventData), AccountingNewsArticle.class);
            repository.save(article);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
