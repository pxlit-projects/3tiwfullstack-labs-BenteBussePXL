package be.pxl.microservices.notificationservice.api.data;

public record NotificationRequest(String sender, String receiver, String subject, String message) {
}
