package de.futurecompany.accounting.infrastructure.repository;

import de.futurecompany.accounting.domain.AccountingNewsArticle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingNewsArticleRepository extends CrudRepository<AccountingNewsArticle,String> {
}
