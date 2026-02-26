package de.bayern.bvv.geotopo.osm_notification_service.repository;

import de.bayern.bvv.geotopo.osm_notification_service.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long>, NotificationRepositoryCustom {
    List<NotificationEntity> findByGroup_Id(Long groupId);
}
