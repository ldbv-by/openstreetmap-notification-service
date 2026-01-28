package de.bayern.bvv.geotopo.osm_notification_service.mapper;

import de.bayern.bvv.geotopo.osm_notification_service.entity.NotificationEntity;
import de.bayern.bvv.geotopo.osm_notification_service.entity.NotificationGroupEntity;
import de.bayern.bvv.geotopo.osm_notification_service.dto.NotificationGroup;
import de.bayern.bvv.geotopo.osm_notification_service.dto.NotificationState;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;

/**
 * Mapping between {@link NotificationGroupEntity} and {@link NotificationGroup}
 */
@UtilityClass
public class NotificationGroupMapper {

    /**
     * Map notification group entity to notification group.
     */
    public NotificationGroup toDto(NotificationGroupEntity notificationGroupEntity) {
        if (notificationGroupEntity == null) return null;

        Set<String> receivers = new HashSet<>();
        Long cntNotifications = 0L;
        Long cntNotificationsClosed = 0L;

        if (notificationGroupEntity.getNotifications() != null) {
            for (NotificationEntity notification : notificationGroupEntity.getNotifications()) {
                receivers.add(notification.getReceiver());
                cntNotifications++;

                if (notification.getState() == NotificationState.CLOSED) {
                    cntNotificationsClosed++;
                }
            }
        }

        return new NotificationGroup(
                notificationGroupEntity.getId(),
                notificationGroupEntity.getState(),
                notificationGroupEntity.getDescription(),
                receivers.isEmpty() ? null : String.join("|", receivers),
                cntNotifications,
                cntNotificationsClosed
        );
    }
}
