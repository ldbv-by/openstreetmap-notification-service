package de.bayern.bvv.geotopo.osm_notification_service.dto;

public record NotificationFilter(
        BoundingBox boundingBox,
        NotificationState state,
        Long groupId,
        NotificationState groupState
) {}
