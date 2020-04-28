# SPI resource

An example of a custom event listener that logs all events.  
Also contains an example of a transaction when acting on a certain event.

## Keycloak config

Copy the jar in the target folder to the `/opt/jboss/keycloak/standalone/deployments/` folder.
Or when using Docker mount the file `./jar-name.jar:/opt/jboss/keycloak/standalone/deployments/jar-name.jar`

## Test

    curl -i --request GET http://localhost:8088/auth/realms/master/example/users/admin --header "Accept: application/json"

    curl -i --request GET http://localhost:8088/auth/realms/master/example/users/admin/alter --header "Accept: application/json"
