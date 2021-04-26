package de.futurecompany.image.application.outboundservices;

import de.futurecompany.image.infrastructure.broker.ImageAssignEventSource;
import de.futurecompany.sharedDomain.event.ImageAssignedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ImageAssignEventPublisherService {
    @Autowired
    ImageAssignEventSource eventSource;

    /**
     * It listen to the created image event which is raised by Image component
     * @param event is a {@link ImageAssignedEvent}
     */
    @EventListener
    public void handleImageAssignedEvent(ImageAssignedEvent event) {
        eventSource.publishAssignedImage(event.getImageAssignedEventData());
    }
}
