package de.futurecompany.accounting.application.queryservices;

import de.futurecompany.accounting.domain.Account;
import de.futurecompany.accounting.domain.AccountView;
import de.futurecompany.accounting.infrastructure.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AccountingSummaryQueryService {

    @Autowired
    AccountRepository accountRepository;

    /**
     * Get list of account to pay or not
     * @return is a {@link AccountView}
     */
    public Flux<AccountView> getSummary() {
        Iterable<Account> all = accountRepository.findAll();

        Flux<AccountView> accountViewFlux = Flux.fromIterable(StreamSupport.stream(all.spliterator(), false)
                .filter(account -> account.isPublished() && account.getPrice() > 0)
                .map(this::map)
                .collect(Collectors.toList()));
        return accountViewFlux;
    }

    private AccountView map(Account account) {
        return AccountView.builder()
                .articleId(account.getArticleId())
                .imageUrl(account.getImageUrl())
                .price(account.getPrice())
                .published(account.isPublished())
                .paid(account.isPaid())
                .Author(account.getAuthor()).build();
    }
}
