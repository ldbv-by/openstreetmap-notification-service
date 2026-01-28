package de.bayern.bvv.geotopo.osm_notification_service.entity;

import de.bayern.bvv.geotopo.osm_notification_service.dto.NotificationState;
import de.bayern.bvv.geotopo.osm_notification_service.dto.NotificationType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Geometry;

import java.time.Instant;

@Entity
@Table(name = "notifications", schema = "notification_data")
@Data
@ToString(exclude = { "group", "geom" })
@EqualsAndHashCode(exclude = { "group", "geom" })
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_generator")
    @SequenceGenerator(name = "notification_generator", sequenceName = "notification_seq", allocationSize = 1, schema = "notification_data")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false, foreignKey = @ForeignKey(name = "fk_notification_group"))
    private NotificationGroupEntity group;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String receiver;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String content;

    @Column(name = "geom", columnDefinition = "Geometry(Geometry, 4326)")
    @JdbcTypeCode(SqlTypes.GEOMETRY)
    private Geometry geom;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "modified_at")
    private Instant modifiedAt;
}
