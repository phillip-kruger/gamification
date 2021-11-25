package com.github.phillipkruger.rest;

import com.github.phillipkruger.model.Score;
import com.github.phillipkruger.service.ScoreService;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationScoped
@Path("/score")
@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Score service",description = "Score Services")
public class ScoreRestApi {
    
    @Inject 
    ScoreService scoreService;
    
    @GET 
    @Path("/{idNumber}")
    @Operation(description = "Get a person's scores using the person's Id Number")
    public List<Score> getScores(@PathParam("idNumber") String idNumber){
        return scoreService.getScores(idNumber);
    }
}