# Theme minimal

An example of a custom theme with only minimal changes.

Changes in this theme

* The h1 tag in the `theme-minimal/welcome/index.ftl` file has been changed, `minimal` has been added to it.
  * You can check this on `http://localhost:8088/auth/`
* The default email templates are copied from the Keycloak theme base into this theme so we can customize them.
  * An extra variable `testNewVariable` has been added to `theme-minimal/email/<html AND text dir>/email-test.ftl`
    * Delete this variable if you don't use the `spi-mail-template-override` otherwise it will fail
  * The parent has been removed from the `theme-minimal/email/theme.properties` file and locales have been added

## Keycloak config

Copy the jar in the target folder to the `/opt/jboss/keycloak/standalone/deployments/` folder.
Or when using Docker mount the file `./jar-name.jar:/opt/jboss/keycloak/standalone/deployments/jar-name.jar`

## Keycloak admin console configuration

The theme can be changed in realm -> realm settings -> themes [tab] or can be passed through environment variables, take a look at the  docker-compose file.

## Live edit the theme

Uncomment the following line in the docker-compose file

    - ./theme-minimal/src/main/resources/theme/theme-minimal:/opt/jboss/keycloak/themes/theme-minimal

Comment the following line in the docker-compose file

    - ./theme-minimal/target/theme-minimal-0.0.1-SNAPSHOT.jar:/opt/jboss/keycloak/standalone/deployments/theme-minimal-0.0.1-SNAPSHOT.jar
    
Run docker-compose from the root dir of this repo

You can now make changes to the theme and when you refresh the page the changes should be immediately visible.
