package com.appstruction.example.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rest/v1")
public interface QuarkusRestServiceAPI {

    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    Response hello();

    @Path("/hello/{name}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    Response helloUser(@PathParam("name") final String name);

    @Path("/random")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    Response randomNumber(final String rangeAsJson);
}
