package de.futurecompany.publishing.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PublishingView {
    private String articleId;
    private String title;
    private List<String> authors;
    private boolean published;
}
