package de.bayern.bvv.geotopo.osm_notification_service.dto;

import org.locationtech.jts.geom.Geometry;

import java.time.Instant;

public record Notification (
    Long id,
    NotificationType type,
    NotificationState state,
    NotificationGroup group,
    String color,
    String receiver,
    String sender,
    String subject,
    String content,
    Geometry geom,
    Instant createdAt,
    Instant modifiedAt
) {}
