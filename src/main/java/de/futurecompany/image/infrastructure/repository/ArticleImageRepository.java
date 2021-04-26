package de.futurecompany.image.infrastructure.repository;

import de.futurecompany.image.domain.ArticleImage;
import de.futurecompany.image.domain.ArticleImageId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleImageRepository extends CrudRepository<ArticleImage, ArticleImageId> {
}
