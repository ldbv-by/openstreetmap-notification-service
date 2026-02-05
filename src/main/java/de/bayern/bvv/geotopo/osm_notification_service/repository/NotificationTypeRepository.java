package de.bayern.bvv.geotopo.osm_notification_service.repository;

import de.bayern.bvv.geotopo.osm_notification_service.entity.NotificationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTypeRepository extends JpaRepository<NotificationTypeEntity, String> {}
