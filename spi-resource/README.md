# SPI resource

An example of a custom REST resource.

this example will add a new base endpoint `auth/realms/<REALM>/custom

## Keycloak config

Copy the jar in the target folder to the `/opt/jboss/keycloak/standalone/deployments/` folder.
Or when using Docker mount the file `./jar-name.jar:/opt/jboss/keycloak/standalone/deployments/jar-name.jar`

## Test

Run one of the scripts in the root of this module to test the our example endpoint.

    // call auth/realms/<REALM>/custom/users/<user> with an anonymous user
    ./test-anonymous-user.sh
    
    // call auth/realms/<REALM>/custom/users/<user> with an authenticated user
    // you can switch variables in the script to test different use cases
    ./test-authenticated-user.sh
