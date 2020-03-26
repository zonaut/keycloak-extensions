# Keycloak extensions

## custom registration form action

Add the following in your `standalone-ha.xml` because we need this to make use of org.keycloak.services.validation.Validation.
You could choose to drop this but than you will need to provide an alternate for Validation... methods.

```xml
    <subsystem xmlns="urn:jboss:domain:ee:4.0">
        ...
        <global-modules>
            <module name="org.keycloak.keycloak-services"/>
        </global-modules>
        ...
    </subsystem>
 ```

### Build

    ./mvnw clean install

Copy `target/placeholder-keycloak-extensions.jar` to the `/opt/jboss/keycloak/standalone/deployments/` folder.
Or when using Docker mount the file `./placeholder-keycloak-extensions.jar:/opt/jboss/keycloak/standalone/deployments/placeholder-keycloak-extensions.jar`

### Configuration

* Open Keycloak administration and select your realm
* Open up Authentication -> Flows [tab]
* Select registration flow and click copy, give it a name and press Ok
* Click the Actions dropdown menu to the far right of 'Copy Of Registration Registration Form' and select 'Add execution'
* Select our custom profile validation from the the list of providers and press save
* Move our custom profile validation step under the default 'Profile validation' step with the up and down buttons
* Set the the default 'Profile validation' to DISABLED and out custom one to REQUIRED
* Click the Bindings tab and select our custom registration flow from the Registration flow dropdown and click save
* Remove or set a style of display:none on the first and lastname div elements in <THEME>/login/register.ftl
