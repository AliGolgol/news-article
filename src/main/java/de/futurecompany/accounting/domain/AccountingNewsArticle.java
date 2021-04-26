package de.futurecompany.accounting.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountingNewsArticle {
    @Id
    private String articleId;

    private String title;

    private String text;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<AccountingArticleAuthor> authors;
}
