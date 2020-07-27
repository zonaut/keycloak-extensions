# Provider domain

An example of adding new domain entities  

This will create the following new table

* prfx_products

## Keycloak config

Add the following in your `standalone-ha.xml`

```xml
<subsystem xmlns="urn:jboss:domain:ee:5.0">
    ...
    <global-modules>
        <module name="org.keycloak.keycloak-model-jpa"/>
        <module name="org.hibernate"/>
    </global-modules>
    ...
</subsystem>
 ```

Copy the jar in the target folder to the `/opt/jboss/keycloak/standalone/deployments/` folder.
Or when using Docker mount the file `./jar-name.jar:/opt/jboss/keycloak/standalone/deployments/jar-name.jar`
