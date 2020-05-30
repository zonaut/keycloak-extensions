package com.zonaut.keycloak.extensions.resource;

import org.jboss.logging.Logger;
import org.keycloak.models.ClientModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.services.managers.AppAuthManager;
import org.keycloak.services.managers.AuthenticationManager;
import org.keycloak.services.managers.RealmManager;
import org.keycloak.sessions.AuthenticationSessionModel;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import java.util.stream.Collectors;

public class AuthCheck {

    private static final Logger log = Logger.getLogger(AuthCheck.class);

    public static void whoAmI(KeycloakSession session) {
        final AuthenticationManager.AuthResult authResult = new AppAuthManager().authenticateBearerToken(session);
        if (authResult == null) {
            log.infof("Anonymous user entering realm %s", session.getContext().getRealm().getName());
        } else {
            ClientModel client = session.getContext().getRealm().getClientByClientId(authResult.getToken().getIssuedFor());
            log.infof("%s, realm: %s, client: %s", authResult.getUser().getUsername(), session.getContext().getRealm().getName(), client.getClientId());
            log.infof("Realm roles: %s", authResult.getUser().getRealmRoleMappings().stream().map(RoleModel::getName).collect(Collectors.toSet()));
        }
    }

    public static void hasRole(KeycloakSession session, String role) {
        final AuthenticationManager.AuthResult authResult = new AppAuthManager().authenticateBearerToken(session);
        isAuthenticated(authResult);

        if (authResult.getToken().getRealmAccess() == null || !authResult.getToken().getRealmAccess().isUserInRole(role)) {
            throw new ForbiddenException("You do not have the required credentials for this action");
        }
    }

    public static void isClient(KeycloakSession session, String clientName) {
        final AuthenticationManager.AuthResult authResult = new AppAuthManager().authenticateBearerToken(session);
        isAuthenticated(authResult);

        ClientModel client = session.getContext().getRealm().getClientByClientId(authResult.getToken().getIssuedFor());
        if (!client.getClientId().equals(clientName)) {
            throw new ForbiddenException("You do not have the required credentials for this action");
        }
    }

    public static void isAuthenticated(KeycloakSession session) {
        final AuthenticationManager.AuthResult authResult = new AppAuthManager().authenticateBearerToken(session);
        isAuthenticated(authResult);
    }

    public static void isAuthenticated(AuthenticationManager.AuthResult authResult) {
        if (authResult == null) {
            throw new NotAuthorizedException("Bearer token required");
        }
    }

}
