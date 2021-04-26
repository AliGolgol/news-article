package de.futurecompany.publishing.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class PublishingAuthor {
    @Id
    private String id;
    private String authorName;
}
