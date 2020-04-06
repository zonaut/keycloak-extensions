package com.zonaut.keycloak.extensions.events.logging;

import org.jboss.logging.Logger;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RealmProvider;
import org.keycloak.models.UserModel;

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
        log.info("## NEW DEFAULT EVENT");
        log.info("-----------------------------------------------------------");
        event.getDetails().forEach((key, value) -> log.info(key + ": " + value));

        // USE CASE SCENARIO, I'm sure there are better use case scenario's :p
        //
        // Let's assume for whatever reason you only want the user
        // to be able to verify his account if a transaction we make succeeds.
        // Let's say an external call to a service needs to return a 200 response code or we throw an exception.

        // When the user tries to login after a failed attempt,
        // the user remains unverified and when trying to login will receive another verify account email.

        if (EventType.VERIFY_EMAIL.equals(event.getType())) {
            RealmModel realm = this.model.getRealm(event.getRealmId());
            UserModel user = this.session.users().getUserById(event.getUserId(), realm);
            if (user != null && user.getEmail() != null && user.isEmailVerified()) {
                log.info("USER HAS VERIFIED EMAIL : " + event.getUserId());

                UserUuidDto userUuidDto = new UserUuidDto(event.getType().name(), event.getUserId(), user.getEmail());
                UserVerifiedTransaction userVerifiedTransaction = new UserVerifiedTransaction(userUuidDto);

                // enlistPrepare -> if our transaction fails than the user is NOT verified
                // enlist -> if our transaction fails than the user is still verified
                // enlistAfterCompletion -> if our transaction fails our user is still verified

                session.getTransactionManager().enlistPrepare(userVerifiedTransaction);
            }
        }
        log.info("-----------------------------------------------------------");
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        log.info("## NEW ADMIN EVENT");
        log.info("-----------------------------------------------------------");
        log.info("Resource pathdd" + ": " + adminEvent.getResourcePath());
        log.info("Resource type" + ": " + adminEvent.getResourceType());
        log.info("Operation type" + ": " + adminEvent.getOperationType());
        log.info("-----------------------------------------------------------");
    }

    @Override
    public void close() {
        // Nothing to close
    }

}
