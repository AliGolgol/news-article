package de.futurecompany.accounting.application.commandservices;

import com.google.gson.Gson;
import de.futurecompany.accounting.domain.Account;
import de.futurecompany.accounting.infrastructure.repository.AccountRepository;
import de.futurecompany.sharedDomain.event.ImageAssignedEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountAssignedCommandService {

    @Autowired
    AccountRepository repository;

    /**
     * Assign an image to an article
     * @param eventData is {@link ImageAssignedEventData}
     */
    public void assignedImage(ImageAssignedEventData eventData){
        Gson gson = new Gson();
        try {
            Account account = gson.fromJson(gson.toJson(eventData), Account.class);
//            Optional<Account> query = repository.findById(eventData.getImageUrl());
//            if (!query.isEmpty()  ){
//                return;
//            }
            repository.save(account);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
