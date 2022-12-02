package ch.zli.m223.controller;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.service.SessionService;

@Path("/login")
public class SessionController {
    @Inject
    SessionService sessionService;

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(ApplicationUser applicationUser) {
        return sessionService.checkCredentials(applicationUser);
    }
}
