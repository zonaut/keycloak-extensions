package com.zonaut.keycloak.extensions.events.logging;

import org.jboss.logging.Logger;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmProvider;

public class PlaceholderEventListenerProvider implements EventListenerProvider {

    private static final Logger log = Logger.getLogger(PlaceholderEventListenerProvider.class);

    private KeycloakSession session;
    private RealmProvider model;

    public PlaceholderEventListenerProvider(KeycloakSession session) {
        this.session = session;
        this.model = session.realms();
    }

    @Override
    public void onEvent(Event event) {
        log.info("## New default event");
        log.info("-----------------------------------------------------------");
        event.getDetails().forEach((key, value) -> log.info(key + ": " + value));
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        log.info("## New admin event");
        log.info("-----------------------------------------------------------");
        log.info("Resource pathdd" + ": " + adminEvent.getResourcePath());
        log.info("Resource type" + ": " + adminEvent.getResourceType());
        log.info("Operation type" + ": " + adminEvent.getOperationType());
    }

    @Override
    public void close() {
        // Nothing to close
    }

}
