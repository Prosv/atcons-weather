package atcons.rest;

import atcons.model.DataSource;
import atcons.services.DataSourceService;
import atcons.util.exceptions.DataSourceNotFoundException;
import atcons.util.exceptions.InvalidDataSourceException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/datasources")
public class DataSourceRS {

    @EJB
    private DataSourceService dataSourceService;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        return Response.ok(dataSourceService.listAllDataSources()).build();
    }

    @Path("/add")
    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response add(DataSource dataSource) {
    public Response add(@FormParam("url") String url,
                        @FormParam("type") String type) {

        DataSource dataSource = new DataSource();
        dataSource.setUrl(url);
        dataSource.setType(type);
        try {
            dataSource = dataSourceService.addDataSource(dataSource);
        } catch (InvalidDataSourceException e) {
            System.out.println(e.getMessage());
        }
        return Response.ok("Data source added with id=" + dataSource.getId()).build();
    }

    @Path("/{id:[0-9][0-9]*}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id) {
        DataSource dataSource = dataSourceService.getDataSource(id);
        if (dataSource == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Data source with id=" + id + " not found").build();
        }
        return Response.ok(dataSource).build();
    }

    @Path("/{id:[0-9][0-9]*}/remove")
    @POST
    public Response remove(@PathParam("id") long id) {
        try {
            dataSourceService.removeDataSource(id);
            return Response.ok().build();
        } catch (DataSourceNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("/{id:[0-9][0-9]*}/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, DataSource dataSource) {
        try {
            dataSource.setId(id);
            dataSourceService.updateDataSource(dataSource);
            return Response.ok(dataSource).build();
        } catch (DataSourceNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }



}
