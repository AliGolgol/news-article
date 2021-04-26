package de.futurecompany.accounting.application.commandservices;

import de.futurecompany.accounting.domain.Account;
import de.futurecompany.accounting.domain.AccountingNewsArticle;
import de.futurecompany.accounting.infrastructure.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountCommandService {

    @Autowired
    AccountRepository accountRepository;

    /**
     * Get an Account object to create and persist it
     * @param account is {@link Account}
     * @return a {@link Mono<Account>}
     */
    public Mono<Account> createAccount(Account account){
        return Mono.justOrEmpty(accountRepository.save(account));
    }

    public Mono<Void> createArticle(AccountingNewsArticle newsArticle){
        return Mono.empty();
    }

}
