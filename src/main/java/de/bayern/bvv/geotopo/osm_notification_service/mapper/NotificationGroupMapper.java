package de.bayern.bvv.geotopo.osm_notification_service.mapper;

import de.bayern.bvv.geotopo.osm_notification_service.entity.NotificationEntity;
import de.bayern.bvv.geotopo.osm_notification_service.entity.NotificationGroupEntity;
import de.bayern.bvv.geotopo.osm_notification_service.model.Notification;
import de.bayern.bvv.geotopo.osm_notification_service.model.NotificationState;
import de.bayern.bvv.geotopo.osm_notification_service.model.NotificationType;
import lombok.experimental.UtilityClass;
import org.locationtech.jts.geom.Geometry;

import java.time.Instant;

/**
 * Mapping between {@link NotificationEntity} and {@link Notification}
 */
@UtilityClass
public class NotificationMapper {

    /**
     * Map notification entity to notification.
     */
    public Notification toDomain(NotificationEntity notificationEntity) {
        if (notificationEntity == null) return null;

        return new Notification(
                notificationEntity.getId(),
                notificationEntity.getType(),
                notificationEntity.getState(),
                notificationEntity.getGroup(),
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
