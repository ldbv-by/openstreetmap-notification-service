package de.bayern.bvv.geotopo.osm_notification_service.model;

public record NotificationGroupFilter(
        NotificationState groupState,
        String description
) {}
