package de.bayern.bvv.geotopo.osm_notification_service.dto;

import lombok.Getter;

@Getter
public enum NotificationState {
    OPEN("Offen"),
    CLOSED("Geschlossen");

    NotificationState(String description) {
        this.description = description;
    }

    private final String description;
}
