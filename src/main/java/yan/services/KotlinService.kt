package yan.services

import com.google.gson.Gson
import io.swagger.annotations.Api
import org.slf4j.LoggerFactory
import javax.ws.rs.Path
import javax.ws.rs.GET
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


/**
 * Created by yan on 22/04/2016.
 */
@Path("/kotlin")
@Api("kotlin")
class KotlinService {

     var logger = LoggerFactory.getLogger(KotlinService::class.java)


    @GET
    @Path("/{text}")
    @Produces(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN)
    fun kotlin(@PathParam("text") text:String?) : Response {
        var gson = Gson();
        logger.info("length: " + (text?.length ?: 0))
        return Response.ok(gson.toJson(text)).build();
    }
}
