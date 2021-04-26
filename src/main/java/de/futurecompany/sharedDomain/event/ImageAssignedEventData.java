package de.futurecompany.sharedDomain.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageAssignedEventData {
    private String imageUrl;
    private String articleId;
    private String Author;
    private int price;
}
