# Theme minimal

An example of a custom theme with only minimal changes.

More info on https://www.keycloak.org/docs/latest/server_development/#_themes

Changes in this theme

* The h1 tag in the `theme-minimal/welcome/index.ftl` file has been changed, `minimal` has been added to it.
  * You can check this on `http://localhost:8088/auth/`
* The default email templates are copied from the Keycloak theme base dir into this theme
  * An extra variable `testNewVariable` has been added to `theme-minimal/email/<html AND text dir>/email-test.ftl`
    * Delete this variable if you don't use the `spi-mail-template-override` otherwise it will fail
  * The parent has been removed from the `theme-minimal/email/theme.properties` file and locales have been added
* the default login folder has been copied from the Keycloak theme base dir into this theme
  * The parent has been removed from the `theme-minimal/login/theme.properties` file
* an example on how to access an environment variable in the theme template files
  * a testExternalVar is declared in the theme.properties file to use the assigned environment variable set in the docker-compose file
  * the `theme-minimal/welcome/index.ftl` file uses it as an example

## Keycloak config

Copy the jar in the target folder to the `/opt/jboss/keycloak/standalone/deployments/` folder.
Or when using Docker mount the file `./jar-name.jar:/opt/jboss/keycloak/standalone/deployments/jar-name.jar`

## Keycloak admin console configuration

The theme can be changed in realm -> realm settings -> themes [tab] or can be passed through environment variables, take a look at the  docker-compose file.

## Live edit the theme

Add or change the following in your `standalone-ha.xml` to disable caching

```xml
<subsystem xmlns="urn:jboss:domain:keycloak-server:1.1">
    ...
    <theme>
        <staticMaxAge>-1</staticMaxAge>
        <cacheThemes>false</cacheThemes>
        <cacheTemplates>false</cacheTemplates>
        <welcomeTheme>${env.KEYCLOAK_WELCOME_THEME:keycloak}</welcomeTheme>
        <default>${env.KEYCLOAK_DEFAULT_THEME:keycloak}</default>
        <dir>${jboss.home.dir}/themes</dir>
    </theme>
    ...
</subsystem>
 ```

Uncomment the following line in the docker-compose file

    - ./theme-minimal/src/main/resources/theme/theme-minimal:/opt/jboss/keycloak/themes/theme-minimal

Comment the following line in the docker-compose file

    - ./theme-minimal/target/theme-minimal-0.0.1-SNAPSHOT.jar:/opt/jboss/keycloak/standalone/deployments/theme-minimal-0.0.1-SNAPSHOT.jar
    
Run docker-compose from the root dir of this repo

You can now make changes to the theme and when you refresh the page the changes should be immediately visible.
