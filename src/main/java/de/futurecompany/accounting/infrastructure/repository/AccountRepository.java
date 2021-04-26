package de.futurecompany.accounting.infrastructure.repository;

import de.futurecompany.accounting.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account,String> {
    Optional<Account> findByArticleId(String id);
}
