package de.futurecompany.accounting.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    private String imageUrl;
    private String articleId;
    private String Author;
    private int price;
    private boolean published;
    private boolean paid;
}
