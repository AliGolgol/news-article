package de.futurecompany.accounting.infrastructure.restapi;

import de.futurecompany.accounting.application.queryservices.AccountingSummaryQueryService;
import de.futurecompany.accounting.domain.AccountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class AccountingController {

    @Autowired
    AccountingSummaryQueryService accountingSummaryQueryService;

    @GetMapping("/account/")
    public Flux<AccountView> get(){
        return accountingSummaryQueryService.getSummary();
    }
}
