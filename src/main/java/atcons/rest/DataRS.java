package atcons.rest;

import atcons.model.Data;
import atcons.services.DataService;
import atcons.util.exceptions.DataNotFoundException;
import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/data")
public class DataRS {

    @EJB
    private DataService dataService;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        return Response.ok(dataService.listAllData()).build();
    }

    @Path("/{id:[0-9][0-9]*}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id) {
        Data data = dataService.getData(id);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Data with id=" + id + " not found").build();
        }
        return Response.ok(data).build();
    }

    @Path("/{id:[0-9][0-9]*}/update")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id) {
        Data data = dataService.getData(id);
        try {
            data = dataService.updateData(data);
            return Response.ok(data).build();
        } catch (DataNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("Unable to get data from source: " + data.getSource().getUrl())
                    .build();
        }
    }

    @Path("/update")
    @POST
    public Response updateAll() {
        try {
            dataService.updateAll();
        } catch (IOException e) {
            //TODO:
        } catch (DataNotFoundException e) {
            //TODO:
        }
        return Response.ok().build();
    }

}
