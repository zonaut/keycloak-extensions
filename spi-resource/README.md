# SPI resource

An example of a custom REST resource.

this example will add a new base endpoint `auth/realms/<REALM>/custom

## Keycloak config

Copy the jar in the target folder to the `/opt/jboss/keycloak/standalone/deployments/` folder.
Or when using Docker mount the file `./jar-name.jar:/opt/jboss/keycloak/standalone/deployments/jar-name.jar`

## Test

    curl -i --request GET http://localhost:8088/auth/realms/master/custom/users/admin
    
    curl -i --request GET http://localhost:8088/auth/realms/placeholder/custom/users/jennifer
    curl -i --request GET http://localhost:8088/auth/realms/placeholder/custom/users/robert

