package ch.zli.m223.controller;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.service.UserService;

@Path("/users")
public class ApplicationUserController {

    @Inject
    UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new user.", description = "Creates a new user and persists the data. (Registration)")
    @PermitAll
    public void create(ApplicationUser user) {
        userService.createApplicationUser(user);
    }

}
