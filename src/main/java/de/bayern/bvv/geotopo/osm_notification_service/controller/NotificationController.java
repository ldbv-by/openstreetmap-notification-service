package de.bayern.bvv.geotopo.osm_notification_service.controller;

import de.bayern.bvv.geotopo.osm_notification_service.dto.*;
import de.bayern.bvv.geotopo.osm_notification_service.entity.NotificationEntity;
import de.bayern.bvv.geotopo.osm_notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller of the notification service for storing notices that can, for example, be displayed in JOSM.
 * Example messages: work orders, quality issues, ...
 */
@RestController
@RequestMapping("/osm-notification-service/v1")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * Get all notifications filtered by NotificationFilter.
     */
    @PostMapping("/notifications")
    public ResponseEntity<List<Notification>> getNotifications(@RequestBody NotificationFilter notificationFilter) {
        List<Notification> notifications = this.notificationService.getNotifications(notificationFilter);
        return ResponseEntity.ok(notifications);
    }

    /**
     * Get all notification groups filtered by NotificationGroupFilter.
     */
    @PostMapping("/groups")
    public ResponseEntity<List<NotificationGroup>> getNotificationGroups(@RequestBody NotificationGroupFilter notificationGroupFilter) {
        List<NotificationGroup> notificationGroups = this.notificationService.getNotificationGroups(notificationGroupFilter);
        return ResponseEntity.ok(notificationGroups);
    }

    /**
     * Create new notification.
     */
    @PostMapping("/notification/create")
    public ResponseEntity<String> createNotification(@RequestBody NewNotification newNotification) {
        NotificationEntity notificationEntity = this.notificationService.createNotification(newNotification);
        return new ResponseEntity<>("Notification " + notificationEntity.getId() + " created.", HttpStatus.CREATED);
    }

    /**
     * Delete Notification Group by Description.
     */
    @DeleteMapping("/group/delete")
    public ResponseEntity<String> deleteNotificationGroup(@RequestParam(value = "id", required = false) Long id,
                                                          @RequestParam(value = "description", required = false) String description) {
        Long groupId = this.notificationService.deleteNotificationGroup(id, description);

        if (groupId == null) {
            return ResponseEntity.ok("Notification Group not found.");
        }

        return ResponseEntity.ok("Notification Group " + groupId + " deleted.");
    }

    /**
     * Set notification state 'Open'.
     */
    @PutMapping("/notification/{notificationId}/state/open")
    public ResponseEntity<String> openNotification(@PathVariable Long notificationId) {
        this.notificationService.setState(notificationId, NotificationState.OPEN);
        return ResponseEntity.ok("Notification " + notificationId + " opened");
    }

    /**
     * Set notification state 'Closed'.
     */
    @PutMapping("/notification/{notificationId}/state/close")
    public ResponseEntity<String> closeNotification(@PathVariable Long notificationId) {
        this.notificationService.setState(notificationId, NotificationState.CLOSED);
        return ResponseEntity.ok("Notification " + notificationId + " closed");
    }

    /**
     * Visit Next Notification in Group.
     */
    @PutMapping("/group/{groupId}/visit")
    public ResponseEntity<Notification> visitNextNotificationInGroup(
            @PathVariable Long groupId,
            @RequestParam NotificationState state) {

        Notification notification = this.notificationService.visitNextNotificationInGroup(groupId, state);
        return ResponseEntity.ok(notification);
    }

}
