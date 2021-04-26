package de.futurecompany.image.infrastructure.repository;

import de.futurecompany.image.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image,String> {
}
