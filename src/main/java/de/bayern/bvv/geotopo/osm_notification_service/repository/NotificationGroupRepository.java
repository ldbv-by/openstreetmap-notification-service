package de.bayern.bvv.geotopo.osm_notification_service.repository;

import de.bayern.bvv.geotopo.osm_notification_service.entity.NotificationGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationGroupRepository extends JpaRepository<NotificationGroupEntity, Long>, NotificationGroupRepositoryCustom {
    NotificationGroupEntity findByDescription(String description);
}
