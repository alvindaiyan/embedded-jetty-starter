package com.yan.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by yan on 3/03/16.
 */
@Path("/ping")
public class Ping {


    @GET
    @Path("/{text}")
    public Response ping(
            @PathParam("text") String text
    ) {
        return Response.accepted("<h1>Text: " + text + "</h1>").build();

    }


}
