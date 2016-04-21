package yan.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import yan.model.PingModel;

/**
 * Created by yan on 3/03/16.
 */
@Path("/ping")
@Api(tags = "Ping")
public class Ping {

    @GET
    @Path("/{text}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response ping(
            @PathParam("text") String text
    ) {
        Gson gson = new Gson();

        PingModel pingModel = new PingModel("A Testing Name for Test: " + text, "blah blah blah blah" );

        return Response.status(Response.Status.ACCEPTED).entity(gson.toJson(pingModel)).build();
    }


}
