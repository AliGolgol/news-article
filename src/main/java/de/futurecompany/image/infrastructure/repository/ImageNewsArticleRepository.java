package de.futurecompany.image.infrastructure.repository;

import de.futurecompany.image.domain.ImageNewsArticle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageNewsArticleRepository extends CrudRepository<ImageNewsArticle,String> {
}
