package com.zonaut.keycloak.extensions.mail;

import org.keycloak.Config;
import org.keycloak.email.EmailTemplateProvider;
import org.keycloak.email.EmailTemplateProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.theme.FreeMarkerUtil;

public class PlaceholderFreeMarkerEmailTemplateProviderFactory implements EmailTemplateProviderFactory {

    private FreeMarkerUtil freeMarker;

    @Override
    public EmailTemplateProvider create(KeycloakSession session) {
        return new PlaceholderFreeMarkerEmailTemplateProvider(session, freeMarker);
    }

    @Override
    public void init(Config.Scope config) {
        freeMarker = new FreeMarkerUtil();
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
    }

    @Override
    public void close() {
        freeMarker = null;
    }

    @Override
    public String getId() {
        return "pl-freemarker";
    }

}
