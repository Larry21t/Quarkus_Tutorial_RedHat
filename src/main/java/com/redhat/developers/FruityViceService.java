package com.redhat.developers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/fruit")
@RegisterRestClient //Quarkus weiss so, dass dieses Interface für CDI-Injektion(Abhängigkeiten) als REST-Client verfügbar sein sollte
public interface FruityViceService {
    
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    FruityVice getFruitByName(@PathParam("name") String name);
}
