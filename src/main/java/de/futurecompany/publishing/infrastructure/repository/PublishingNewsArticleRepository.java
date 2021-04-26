package de.futurecompany.publishing.infrastructure.repository;

import de.futurecompany.publishing.domain.PublishingArticle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishingNewsArticleRepository extends CrudRepository<PublishingArticle,String> {
}
