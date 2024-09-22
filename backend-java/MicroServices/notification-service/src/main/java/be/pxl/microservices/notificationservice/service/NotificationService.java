package be.pxl.microservices.notificationservice.service;

import be.pxl.microservices.notificationservice.api.data.NotificationDTO;
import be.pxl.microservices.notificationservice.api.data.NotificationRequest;
import be.pxl.microservices.notificationservice.domain.Notification;
import be.pxl.microservices.notificationservice.exception.NotFoundException;
import be.pxl.microservices.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void createNotification(NotificationRequest notificationRequest){
        Notification newNotification = new Notification(notificationRequest.sender(), notificationRequest.receiver(), notificationRequest.subject(), notificationRequest.message());
        this.notificationRepository.save(newNotification);
    }

    public Notification findNotificationById(long id){
        return this.notificationRepository.findById(id).orElseThrow(() -> new NotFoundException(id + " not found!"));
    }

    public NotificationDTO getNotificationById(long id){
        Notification notification = findNotificationById(id);
        return new NotificationDTO(notification.getId(), notification.getSender(), notification.getReceiver(), notification.getSubject(), notification.getMessage());
    }

    public List<NotificationDTO> getAllNotifications(){
        return this.notificationRepository.findAll()
                .stream().map(notification -> new NotificationDTO(notification.getId(), notification.getSender(), notification.getReceiver(), notification.getSubject(), notification.getMessage())).toList();
    }

    public void updateNotification(long id, NotificationDTO notificationDTO){
        Notification notification = findNotificationById(id);
        notification.setSender(notificationDTO.sender());
        notification.setReceiver(notificationDTO.receiver());
        notification.setSubject(notificationDTO.subject());
        notification.setMessage(notificationDTO.message());
        this.notificationRepository.save(notification);
    }

    public void deleteNotification(long id){
        Notification notification = findNotificationById(id);
        this.notificationRepository.delete(notification);
    }
}
