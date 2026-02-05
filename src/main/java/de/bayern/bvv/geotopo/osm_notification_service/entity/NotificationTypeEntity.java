package de.bayern.bvv.geotopo.osm_notification_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "notification_types", schema = "notification_data")
@Data
public class NotificationTypeEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String description;
}
