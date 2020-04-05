# Keycloak extensions

Keycloak extension examples

* [spi-event-listener](spi-event-listener/README.md)
  * example of event listener
* [spi-registration-profile](spi-registration-profile/README.md)
  * disable first and last name in registration page
* [theme-minimal](theme-minimal/README.md)
  * minimal changes to default theme

## Build

Build all

    ./mvnw clean install

Build single module

    ./mvnw clean install -pl spi-event-listener
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
  * Look in the _resources/demo-config/import-dir/placeholder-users-0.json file for available users
  * Password is always 'password' for those users
* Mailhog will be available on http://localhost:8025/
