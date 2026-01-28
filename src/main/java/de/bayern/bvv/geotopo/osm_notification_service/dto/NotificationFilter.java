package de.bayern.bvv.geotopo.osm_notification_service.model;

public record NotificationFilter(
        BoundingBox boundingBox,
        NotificationState state,
        Long groupId,
        NotificationState groupState
) {}
