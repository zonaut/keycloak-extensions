package com.zonaut.keycloak.extensions.resource;

import org.jboss.logging.Logger;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserModel;
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.services.resource.RealmResourceProvider;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class CustomResourceProvider implements RealmResourceProvider {

    private static final Logger log = Logger.getLogger(CustomResourceProvider.class);

    private final KeycloakSession session;

    public CustomResourceProvider(KeycloakSession session) {
        this.session = session;
    }

    @GET
    @Path("users/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUsersByName(
            @QueryParam("auth_check_type") AuthCheckType authCheckType,
            @PathParam("name") String name) {

        AuthCheck.whoAmI(session);

        // An example of different ways to check authorization for a user/client
        log.infof("Checking authorization for %s", authCheckType);
        switch (authCheckType) {
            case ANONYMOUS:
                // Nothing to do :p
                break;
            case AUTHENTICATED:
                AuthCheck.isAuthenticated(session);
                break;
            case AUTHENTICATED_WITH_ROLE:
                AuthCheck.hasRole(session, "product_view");
                break;
            case AUTHENTICATED_WITH_CLIENT:
                AuthCheck.isClient(session, "client-two");
                break;
            default:
                throw new BadRequestException("Auth type is unknown");
        }

//        // Get users based on an attribute
//        final List<UserModel> users =  session
//                .userStorageManager()
//                .searchForUserByUserAttribute("some-attribute", name, session.getContext().getRealm());

        final List<UserModel> users = session
                .userStorageManager()
                .searchForUser(name, session.getContext().getRealm());

        // Transform our model to representations that can be serialized.
        List<UserRepresentation> representations = new ArrayList<>(users.size());
        for (UserModel user : users) {
            representations.add(ModelToRepresentation.toRepresentation(session, session.getContext().getRealm(), user));
        }

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
