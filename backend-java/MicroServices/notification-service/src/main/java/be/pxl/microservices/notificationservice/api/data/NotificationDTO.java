package be.pxl.microservices.notificationservice.api.data;

public record NotificationDTO(long id, String sender, String receiver, String subject, String message) {
}
