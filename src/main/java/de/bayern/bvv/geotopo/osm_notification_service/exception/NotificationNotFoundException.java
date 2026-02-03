package de.bayern.bvv.geotopo.osm_notification_service.exception;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException(String message) {
        super(message);
    }
}