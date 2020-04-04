# Keycloak extensions

Keycloak extension examples

* [spi-registration-profile](spi-registration-profile/README.md)
  * disable first and last name in registration page
* [theme-minimal](theme-minimal/README.md)
  * minimal changes to default theme

## Build

Build all

    ./mvnw clean install

Build single module

    ./mvnw clean install -pl spi-registration-profile
    
    ./mvnw clean install -pl theme-minimal
