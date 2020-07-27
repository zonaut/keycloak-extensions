# SPI mail template override

An example on how to change the default behaviour of the email templates and add extra variables to it.

**INFO** Keycloak sends multipart MIME messages so an email always contains both text and html.

## Keycloak config

Add the following in your `standalone-ha.xml`

```xml
<subsystem xmlns="urn:jboss:domain:ee:5.0">
    ...
    <global-modules>
        <module name="org.freemarker"/>
    </global-modules>
    ...
</subsystem>
 ```
Also add the following to disable the default email template provider

```xml
<subsystem xmlns="urn:jboss:domain:keycloak-server:1.1">
    <web-context>auth</web-context>
    ...
    <spi name="emailTemplate">
        <provider name="freemarker" enabled="false"/>
    </spi>
    ...
</subsystem>
 ```

Copy the jar in the target folder to the `/opt/jboss/keycloak/standalone/deployments/` folder.
Or when using Docker mount the file `./jar-name.jar:/opt/jboss/keycloak/standalone/deployments/jar-name.jar`

## Keycloak admin console configuration

Nothing is required as the provider will be picked up immediately when the default one is disabled.

You can check if it's configured in the Keycloak admin console.
User dropdown in right top corner -> server info -> providers [tab] -> search for `emailTemplate`, you should see the factory id `pl-freemarker`

## Example usage

When using docker-compose Mailhog is started, you can check email send by Keycloak in there.

Add a new variable `${testNewVariable}` in the `<THEME>/email/<html AND text dir>/email-test.ftl` template file.

When send a test email from realm -> realm settings -> email [tab] you should receive the test email with the new variable filled in.
