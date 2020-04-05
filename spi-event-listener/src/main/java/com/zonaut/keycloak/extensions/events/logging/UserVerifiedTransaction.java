package com.zonaut.keycloak.extensions.events.logging;

import org.jboss.logging.Logger;
import org.keycloak.models.AbstractKeycloakTransaction;

public class UserVerifiedTransaction extends AbstractKeycloakTransaction {

    private static final Logger log = Logger.getLogger(UserVerifiedTransaction.class);

    private final UserUuidDto userUuidDto;

    public UserVerifiedTransaction(UserUuidDto userUuidDto) {
        this.userUuidDto = userUuidDto;
    }

    @Override
    protected void commitImpl() {
        log.info("## USER VERIFIED TRANSACTION");
        log.info("-----------------------------------------------------------");
        log.info(this.userUuidDto.toString());
        log.info("-----------------------------------------------------------");

        // You could make a http call here and send the object.
        // When we thrown an exception here, the user would not be verified when using .enlistPrepare
        //throw new RuntimeException("External call failed!");
    }

    @Override
    protected void rollbackImpl() {
        //
    }

}
