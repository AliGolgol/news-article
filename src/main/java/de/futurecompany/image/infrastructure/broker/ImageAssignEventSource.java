package de.futurecompany.image.infrastructure.broker;

import de.futurecompany.accounting.application.commandservices.AccountAssignedCommandService;
import de.futurecompany.sharedDomain.event.ImageAssignedEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageAssignEventSource {
    @Autowired
    AccountAssignedCommandService accountAssignedCommandService;

    /**
     * It publish an event to the Accounting component
     * @param event is a {@link ImageAssignedEventData}
     */
    public void publishAssignedImage(ImageAssignedEventData event){
        accountAssignedCommandService.assignedImage(event);
    }
}
