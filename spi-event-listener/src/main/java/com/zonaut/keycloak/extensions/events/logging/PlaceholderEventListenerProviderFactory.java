package com.zonaut.keycloak.extensions.events.logging;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class PlaceholderEventListenerProviderFactory implements EventListenerProviderFactory {

    @Override
    public PlaceholderEventListenerProvider create(KeycloakSession keycloakSession) {
        return new PlaceholderEventListenerProvider(keycloakSession);
    }

    @Override
    public void init(Config.Scope scope) {
        //
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
        //
    }

    @Override
    public void close() {
        //
    }

    @Override
    public String getId() {
        return "pl_event_listener";
    }

}
