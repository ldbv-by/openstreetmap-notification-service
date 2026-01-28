package de.bayern.bvv.geotopo.osm_notification_service.repository;

import de.bayern.bvv.geotopo.osm_notification_service.entity.NotificationEntity;
import de.bayern.bvv.geotopo.osm_notification_service.model.NotificationFilter;

import java.util.List;

public interface NotificationRepositoryCustom {
    List<NotificationEntity> findByNotificationFilter(NotificationFilter notificationFilter);
}
