package de.bayern.bvv.geotopo.osm_notification_service.dto;

public record NotificationGroup(
    Long id,
    NotificationState state,
    String description,
    String receiver,
    Long cntNotifications,
    Long cntNotificationsClosed
) {}
