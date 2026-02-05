package de.bayern.bvv.geotopo.osm_notification_service.mapper;

import de.bayern.bvv.geotopo.osm_notification_service.entity.NotificationEntity;
import de.bayern.bvv.geotopo.osm_notification_service.dto.Notification;
import lombok.experimental.UtilityClass;

/**
 * Mapping between {@link NotificationEntity} and {@link Notification}
 */
@UtilityClass
public class NotificationMapper {

    /**
     * Map notification entity to notification.
     */
    public Notification toDto(NotificationEntity notificationEntity) {
        if (notificationEntity == null) return null;

        return new Notification(
                notificationEntity.getId(),
                notificationEntity.getType().getId(),
                notificationEntity.getType().getDescription(),
                notificationEntity.getState(),
                NotificationGroupMapper.toDto(notificationEntity.getGroup()),
                notificationEntity.getColor(),
                notificationEntity.getReceiver(),
                notificationEntity.getSender(),
                notificationEntity.getSubject(),
                notificationEntity.getContent(),
                notificationEntity.getGeom(),
                notificationEntity.getCreatedAt(),
                notificationEntity.getModifiedAt()
        );
    }
}
