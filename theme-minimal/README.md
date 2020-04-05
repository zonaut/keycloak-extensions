# Theme minimal

An example of a custom theme with only minimal changes.

Only the h1 tag in the minimal-theme/welcome/index.ftl file has been changed.

## Keycloak config

Copy the jar in the target folder to the `/opt/jboss/keycloak/standalone/deployments/` folder.
Or when using Docker mount the file `./jar-name.jar:/opt/jboss/keycloak/standalone/deployments/jar-name.jar`

## Live edit the theme

Uncomment the following line in the docker-compose file

    - ./theme-minimal/src/main/resources/theme/theme-minimal:/opt/jboss/keycloak/themes/theme-minimal

Comment the following line in the docker-compose file

    - ./theme-minimal/target/theme-minimal-0.0.1-SNAPSHOT.jar:/opt/jboss/keycloak/standalone/deployments/theme-minimal-0.0.1-SNAPSHOT.jar
    
Run docker-compose from the root dir of this repo

You can now make changes to the theme and when you refresh the page the changes should be immediately visible.
