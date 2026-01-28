package de.bayern.bvv.geotopo.osm_notification_service.dto;

public record BoundingBox(
        double maxX,
        double maxY,
        double minX,
        double minY
) {}
