package be.pxl.microservices.notificationservice.api;

import be.pxl.microservices.notificationservice.api.data.NotificationDTO;
import be.pxl.microservices.notificationservice.api.data.NotificationRequest;
import be.pxl.microservices.notificationservice.domain.Notification;
import be.pxl.microservices.notificationservice.exception.NotFoundException;
import be.pxl.microservices.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<?> createNotification(@RequestBody NotificationRequest notificationRequest){
        try {
            this.notificationService.createNotification(notificationRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getNotificationById(@PathVariable long id){
        try {
            NotificationDTO notificationDTO = this.notificationService.getNotificationById(id);
            return new ResponseEntity<>(notificationDTO, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllNotifications(){
        try {
            List<NotificationDTO> notificationDTOList = this.notificationService.getAllNotifications();
            return new ResponseEntity<>(notificationDTOList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateNotification(@PathVariable long id, @RequestBody NotificationDTO notificationDTO){
        try {
            this.notificationService.updateNotification(id, notificationDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable long id){
        try {
            this.notificationService.deleteNotification(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
