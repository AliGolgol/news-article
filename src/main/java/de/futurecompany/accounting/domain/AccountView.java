package de.futurecompany.accounting.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountView {
    private String imageUrl;
    private String articleId;
    private String Author;
    private int price;
    private boolean published;
    private boolean paid;
}
