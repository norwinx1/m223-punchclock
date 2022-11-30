package ch.zli.m223.controller;

import java.util.List;

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

import ch.zli.m223.model.Category;
import ch.zli.m223.service.CategoryService;

@Path("/categories")
@Tag(name = "Categories", description = "Handling of categories")
public class CategoryController {

    @Inject
    CategoryService CategoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all categories.", description = "Returns a list of all categories.")
    public List<Category> index() {
        return CategoryService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new Category.", description = "Creates a new Category and returns the newly added Category.")
    public Category create(Category category) {
        return CategoryService.createCategory(category);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Deletes the selected Category.", description = "Deletes the selected Category by the given id.")
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        CategoryService.deleteCategory(Long.parseLong(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get the selected Category.", description = "Get the selected Category by the given id.")
    @Path("/{id}")
    public Category getCategory(@PathParam("id") String id) {
        return CategoryService.find(Long.parseLong(id));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates the Category.", description = "Update the selected Category.")
    public Category updateCategory(Category category) {
        return CategoryService.update(category);
    }
}
