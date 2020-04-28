package com.zonaut.keycloak.extensions.resource;

import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakTransactionManager;
import org.keycloak.models.UserModel;
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.services.resource.RealmResourceProvider;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelloResourceProvider implements RealmResourceProvider {

    private KeycloakSession session;

    public HelloResourceProvider(KeycloakSession session) {
        this.session = session;
    }

    @GET
    @Path("users/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUsersByPartner(@PathParam("name") String partnerName) {
//        final List<UserModel> partnerUsers =  session
//                .userStorageManager()
//                .searchForUserByUserAttribute("partner", partnerName, session.getContext().getRealm());

        final List<UserModel> partnerUsers =  session
                .userStorageManager()
                .searchForUser(partnerName, session.getContext().getRealm());

        List<UserRepresentation> representations = new ArrayList<>(partnerUsers.size());
        for (UserModel user : partnerUsers) {
            representations.add(ModelToRepresentation.toRepresentation(session, session.getContext().getRealm(), user));
        }

        System.out.println("TEST");
        return Response.status(Response.Status.OK).entity(representations).build();
    }

    @GET
    @Path("users/{name}/alter")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUsersByPartnerAlter(@PathParam("name") String partnerName) {
//        final List<UserModel> partnerUsers =  session
//                .userStorageManager()
//                .searchForUserByUserAttribute("partner", partnerName, session.getContext().getRealm());

        final List<UserModel> partnerUsers =  session
                .userStorageManager()
                .searchForUser(partnerName, session.getContext().getRealm());

        List<UserRepresentation> representations = new ArrayList<>(partnerUsers.size());
        for (UserModel user : partnerUsers) {
            representations.add(ModelToRepresentation.toBriefRepresentation(user));
        }

        System.out.println("TEST");
        return Response.status(Response.Status.OK).entity(representations).build();
    }


    @Override
    public Object getResource() {
        return this;
    }

    @Override
    public void close() {

    }

}
