package de.bayern.bvv.geotopo.osm_notification_service.model;

public record NotificationGroup(
    Long id,
    NotificationState state,
    String description,
    String receiver,
    Long cntNotifications,
    Long cntNotificationsClosed
) {}
