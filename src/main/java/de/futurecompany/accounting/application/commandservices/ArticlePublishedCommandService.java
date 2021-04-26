package de.futurecompany.accounting.application.commandservices;

import com.google.gson.Gson;
import de.futurecompany.accounting.domain.Account;
import de.futurecompany.accounting.infrastructure.repository.AccountRepository;
import de.futurecompany.sharedDomain.event.ArticlePublishedEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticlePublishedCommandService {

    @Autowired
    AccountRepository accountRepository;

    /**
     * Get an event form the Publishing component
     * @param eventData is a {@link ArticlePublishedEventData}
     */
    public void publishArticle(ArticlePublishedEventData eventData){
        Gson gson = new Gson();
        try {
            String articleId = gson.fromJson(gson.toJson(eventData.getArticleId()), String.class);
            Optional<Account> account = accountRepository.findByArticleId(articleId);
            if (!account.isEmpty()){
                account.get().setPublished(true);
                accountRepository.save(account.get());
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
