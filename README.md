# Keycloak extensions

Keycloak extension examples.  
Titles with * are already activated when running `docker-compose up`

* [spi-event-listener](spi-event-listener/README.md) *
  * example of a custom event listener
* [spi-mail-template-override](spi-mail-template-override/README.md) *
  * example on how to change default mail behaviour and add extra variables to it.
* [spi-registration-profile](spi-registration-profile/README.md)
  * disable first and last name validation in the registration page
* [theme-minimal](theme-minimal/README.md) *
  * a custom theme with minimal changes

## Build

Build all

    ./mvnw clean install

Build single module

    ./mvnw clean install -pl spi-event-listener
    ./mvnw clean install -pl spi-mail-template-override
    ./mvnw clean install -pl spi-registration-profile
    ./mvnw clean install -pl theme-minimal

## Run with Docker Compose

You need to build all modules first because we mount the jars in the docker-compose file

Run

    docker-compose up

Stop

    CTRL + C
    docker-compose down    

* Keycloak admin will be available on http://localhost:8088/auth/
  * User -> admin
  * Password -> password
* A placeholder realm is available on http://localhost:8088/auth/realms/placeholder
  * Look in the `_resources/demo-config/import-dir/placeholder-users-0.json` file for available users
  * Password is always 'password' for those users
* Mailhog will be available on http://localhost:8025/

## Export realms and users

* Run `docker-compose up`
* Make your changes in Keycloak
* Press `CTRL + C ` but do NOT run docker-compose down
* Uncomment the `-Dkeycloak.migration.action=export...` under commands in the docker-compose file.  
* Comment the `-Dkeycloak.migration.action=import` under commands in the docker-compose file.
* Run `docker-compose up`
* all realms and users will be exported in the `_resources/demo-config/export-dir`
* You can now run `docker-compose down` and revert the changes you made to the docker-compose file
* Copy the contents of the export dir to the import dir if you want to replace them

## Other resources

Don't forget to look in the actual Keycloak code itself because the examples are based on the implementations itself.

* https://github.com/keycloak
* https://github.com/keycloak/keycloak/tree/master/examples
* https://github.com/thomasdarimont/keycloak-extension-playground
