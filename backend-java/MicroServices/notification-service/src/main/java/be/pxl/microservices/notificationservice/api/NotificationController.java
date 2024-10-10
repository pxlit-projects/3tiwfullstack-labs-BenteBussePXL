package be.pxl.microservices.notificationservice.api;

import be.pxl.microservices.notificationservice.domain.Notification;
import be.pxl.microservices.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notifications")
public class NotificationController {

}
