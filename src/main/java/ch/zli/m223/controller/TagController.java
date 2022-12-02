package ch.zli.m223.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.model.Tag;
import ch.zli.m223.service.TagService;

@Path("/tags")
@RolesAllowed({ "User", "Admin" })
public class TagController {

    @Inject
    TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Entries.", description = "Returns a list of all entries.")
    public List<Tag> index() {
        return tagService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new Tag.", description = "Creates a new Tag and returns the newly added Tag.")
    public Tag create(Tag tag) {
        return tagService.createTag(tag);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Deletes the selected Tag.", description = "Deletes the selected Tag by the given id.")
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        tagService.deleteTag(Long.parseLong(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get the selected Tag.", description = "Get the selected Tag by the given id.")
    @Path("/{id}")
    public Tag getTag(@PathParam("id") String id) {
        return tagService.find(Long.parseLong(id));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates the Tag.", description = "Update the selected Tag.")
    public Tag updateTag(Tag tag) {
        return tagService.update(tag);
    }
}
