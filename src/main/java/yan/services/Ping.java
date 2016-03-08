package yan.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;

/**
 * Created by yan on 3/03/16.
 */
@Path("/ping")
@Api(tags = "Ping")
public class Ping {


    @GET
    @Path("/{text}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping(
            @PathParam("text") String text
    ) {
        return Response.status(Response.Status.ACCEPTED).entity("{}").build();
    }


}
