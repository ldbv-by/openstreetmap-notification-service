package de.bayern.bvv.geotopo.osm_notification_service.service;

import de.bayern.bvv.geotopo.osm_notification_service.dto.*;
import de.bayern.bvv.geotopo.osm_notification_service.entity.NotificationEntity;
import de.bayern.bvv.geotopo.osm_notification_service.entity.NotificationGroupEntity;
import de.bayern.bvv.geotopo.osm_notification_service.mapper.NotificationGroupMapper;
import de.bayern.bvv.geotopo.osm_notification_service.mapper.NotificationMapper;
import de.bayern.bvv.geotopo.osm_notification_service.repository.NotificationGroupRepository;
import de.bayern.bvv.geotopo.osm_notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Logic of the notification service.
 */
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationGroupRepository notificationGroupRepository;

    /**
     * Get all notifications filtered by NotificationFilter.
     */
    public List<Notification> getNotifications(NotificationFilter notificationFilter) {
        return this.notificationRepository.findByNotificationFilter(notificationFilter)
                .stream().map(NotificationMapper::toDto)
                .toList();
    }

    /**
     * Get all notification groups filtered by NotificationGroupFilter.
     */
    public List<NotificationGroup> getNotificationGroups(NotificationGroupFilter notificationGroupFilter) {
        return this.notificationGroupRepository.findByNotificationGroupFilter(notificationGroupFilter)
                .stream().map(NotificationGroupMapper::toDto)
                .toList();
    }

    /**
     * Create new notification.
     */
    @Transactional
    public NotificationEntity createNotification(NewNotification newNotification){
        NotificationEntity notification = new NotificationEntity();

        notification.setId(null); // get by sequence
        notification.setState(NotificationState.OPEN);
        notification.setType(NotificationType.fromDescription(newNotification.type().getDescription()));
        notification.setColor(newNotification.color());
        notification.setSubject(newNotification.subject());
        notification.setReceiver(newNotification.receiver());
        notification.setSender(newNotification.sender());
        notification.setContent(newNotification.content());
        notification.setGeom(newNotification.geom());
        notification.setCreatedAt(Instant.now());

        NotificationGroupEntity group = this.notificationGroupRepository.findByDescription(newNotification.groupDescription());
        if (group == null){
            group = this.createNotificationGroup(newNotification.groupDescription());
        }
        notification.setGroup(group);

        return this.notificationRepository.save(notification);
    }

    /**
     * Create new notification group.
     */
    private NotificationGroupEntity createNotificationGroup(String groupDescription){
        NotificationGroupEntity group = new NotificationGroupEntity();
        group.setId(null);
        group.setState(NotificationState.OPEN);
        group.setDescription(groupDescription);

        return this.notificationGroupRepository.save(group);
    }

    /**
     * Delete Notification Group.
     */
    public Long deleteNotificationGroup(Long groupId, String groupDescription){
        NotificationGroupEntity group;
        if (groupId != null) {
            group = this.notificationGroupRepository.findById(groupId).orElse(null);
        } else if (groupDescription != null) {
            group = this.notificationGroupRepository.findByDescription(groupDescription);
        } else {
            return null;
        }

        if (group != null) {
            this.notificationGroupRepository.delete(group);
            return group.getId();
        }

        return null;
    }

}
