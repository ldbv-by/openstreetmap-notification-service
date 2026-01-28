package de.bayern.bvv.geotopo.osm_notification_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.bayern.bvv.geotopo.osm_notification_service.dto.NotificationState;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "notification_groups", schema = "notification_data")
@Data
@ToString(exclude = "notifications")
@EqualsAndHashCode(exclude = "notifications")
public class NotificationGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_group_generator")
    @SequenceGenerator(name = "notification_group_generator", sequenceName = "notification_group_seq", allocationSize = 1, schema = "notification_data")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationState state;

    @Column(nullable = false, unique = true)
    private String description;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotificationEntity> notifications;
}
