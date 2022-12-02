package ch.zli.m223.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

import ch.zli.m223.model.ApplicationUser;
import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class SessionService {
    @Inject
    UserService userService;

    public Response checkCredentials(ApplicationUser applicationUser) {
        Optional<ApplicationUser> user = userService.findByUsername(applicationUser.getUsername());
        if (user.isPresent() && user.get().getPassword().equals(applicationUser.getPassword())) {
            String token = Jwt.issuer("https://zli.example.com")
                    .upn(user.get().getUsername())
                    .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                    .expiresIn(Duration.ofHours(12))
                    .sign();
            return ResponseBuilder.ok("Hello " + user.get().getUsername() + "!", MediaType.TEXT_PLAIN_TYPE)
                    .header("Authorization", token)
                    .cookie(new NewCookie("JWT", token))
                    .build().toResponse();
        } else {
            return ResponseBuilder.create(Status.UNAUTHORIZED).build().toResponse();
        }
    }
}
