package de.bayern.bvv.geotopo.osm_notification_service.dto;

public record NotificationGroupFilter(
        NotificationState groupState,
        String description
) {}
