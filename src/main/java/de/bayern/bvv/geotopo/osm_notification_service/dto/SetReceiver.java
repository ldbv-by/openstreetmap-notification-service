package de.bayern.bvv.geotopo.osm_notification_service.dto;

public record SetReceiver(
        Long notificationId,
        String groupDescription,
        String receiver
) { }
