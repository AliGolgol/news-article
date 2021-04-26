package de.futurecompany.newsarticle.domain.commands;

import de.futurecompany.newsarticle.domain.ArticleAuthor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SaveNewsArticleCommand {
    private String title;
    private String text;
    private List<ArticleAuthor> authors;
}
