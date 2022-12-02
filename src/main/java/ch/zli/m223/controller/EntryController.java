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
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.Entry;
import ch.zli.m223.service.EntryService;

@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
@RolesAllowed({ "User", "Admin" })
public class EntryController {

    @Inject
    EntryService entryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Entries.", description = "Returns a list of all entries.")
    public List<Entry> index() {
        return entryService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new entry.", description = "Creates a new entry and returns the newly added entry.")
    public Entry create(Entry entry) {
        return entryService.createEntry(entry);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Deletes the selected entry.", description = "Deletes the selected entry by the given id.")
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        entryService.deleteEntry(Long.parseLong(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get the selected entry.", description = "Get the selected entry by the given id.")
    @Path("/{id}")
    public Entry getEntry(@PathParam("id") String id) {
        return entryService.find(Long.parseLong(id));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates the entry.", description = "Update the selected entry.")
    public Entry updateEntry(Entry entry) {
        return entryService.update(entry);
    }
}
