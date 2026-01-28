package de.bayern.bvv.geotopo.osm_notification_service.repository;

import de.bayern.bvv.geotopo.osm_notification_service.entity.NotificationGroupEntity;
import de.bayern.bvv.geotopo.osm_notification_service.dto.NotificationGroupFilter;

import java.util.List;

public interface NotificationGroupRepositoryCustom {
    List<NotificationGroupEntity> findByNotificationGroupFilter(NotificationGroupFilter notificationGroupFilter);
}
