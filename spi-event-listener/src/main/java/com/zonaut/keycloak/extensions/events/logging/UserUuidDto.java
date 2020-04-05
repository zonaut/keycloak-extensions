package com.zonaut.keycloak.extensions.events.logging;

public class UserUuidDto {

    private String type;
    private String uuid;
    private String email;

    public UserUuidDto(String type, String uuid, String email) {
        this.type = type;
        this.uuid = uuid;
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public String getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserUuidDto{" +
                "type='" + type + '\'' +
                ", uuid='" + uuid + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
