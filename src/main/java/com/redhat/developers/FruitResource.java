package com.redhat.developers;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.quarkus.logging.Log;



@Path("/fruit")
public class FruitResource {
    FruitRepository fruitRepository;

    public FruitResource(FruitRepository fruitRepository){ //Konstruktor
        this.fruitRepository = fruitRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruit> fruits(@QueryParam("season") String season, @QueryParam("name") String name) { //Was bedeutet @QueryParam
        if(name != null){ 
            Log.infof("Searching for %s", name);
            return fruitRepository.findByName(name);
        }
        if(season != null){
            Log.infof("Searching for %s fruits", season);
            return fruitRepository.findBySeason(season);
        }
        return Fruit.listAll();
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newFruit(Fruit fruit) {
        fruit.id = null;
        fruit.persist();
        return Response.status(Status.CREATED).entity(fruit).build(); //Was macht diese Zeile genau? Status zurückgeben? -> der Status ist created (HTTP_Status 201), aber das danach?
    }
    


}
