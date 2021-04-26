package de.futurecompany.image.domain.command;

import lombok.Data;

@Data
public class ImageAssignedCommand {
    private String imageUrl;
    private String articleId;
    private String Author;
    private int price;
    private boolean published;
    private boolean paid;
}
