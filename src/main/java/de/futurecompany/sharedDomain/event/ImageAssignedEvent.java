package de.futurecompany.sharedDomain.event;

import lombok.Data;

@Data
public class ImageAssignedEvent {
    private ImageAssignedEventData imageAssignedEventData;

    public ImageAssignedEvent(ImageAssignedEventData imageAssignedEventData) {
        this.imageAssignedEventData = imageAssignedEventData;
    }
}
