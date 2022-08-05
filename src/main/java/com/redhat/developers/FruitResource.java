package com.redhat.developers;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.quarkus.logging.Log;



@Path("/fruit") //Legt den URL-Pfad(-endung) fest, zu welchem die Ressource gehört bzw. sie abgerufen werden kann.
public class FruitResource {
    FruitRepository fruitRepository;

    public FruitResource(FruitRepository fruitRepository){ //Konstruktor
        this.fruitRepository = fruitRepository;
    }

    @GET //Die Methode mit diesem Dekorator kann HTTP-GET-Requests generieren, also vom Server eine Ressource abrufen
    @Produces(MediaType.APPLICATION_JSON) //Definiert Medientyp, welchen die Ressource erstellen kann und als Antwort an Client schickt. 
    public List<Fruit> fruits(@QueryParam("season") String season, @QueryParam("name") String name) { //@QueryParam = Parameter aus Query(?season=Summer) in der URL 
        if(name != null){ 
            Log.infof("Searching for %s", name); //Wo wird das protokolliert? -> Wenn man im Development-Modus ist im Terminal
            return fruitRepository.findByName(name);
        }
        if(season != null){
            Log.infof("Searching for %s fruits", season);
            return fruitRepository.findBySeason(season);
        }
        return Fruit.listAll();
    }



    @GET//Die folgende Funktion "getFruit" habe ich selber hinzugefügt -> nicht im Tutorial
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruit> getFruit(@PathParam("name") String name){ //Der URL kann nach /fruits noch einen Früchtenamen(z.B. /Apple) angehängt werden und dann wird die spezifische Frucht angzeigt. 
        if(name != null){                                        //Dieses if braucht es nicht oder? Es würde reichen "return fruitRepository.findByName(name);"
            return fruitRepository.findByName(name);
        }
        return Fruit.listAll();
    }



    @Transactional //Kennzeichnet Übertragung von Daten
    @POST //Diese Methode mit dem Dekorator, kann HTTP-Post-Requests generieren
    @Consumes(MediaType.APPLICATION_JSON) //Definiert welchen Medientyp Server akzeptiert
    @Produces(MediaType.APPLICATION_JSON)
    public Response newFruit(Fruit fruit) {
        fruit.id = null;
        fruit.persist(); //Tut den Datensatz in die Datenbank
        return Response.status(Status.CREATED).entity(fruit).build(); //Was macht diese Zeile genau? Status zurückgeben? -> der Status ist created (HTTP_Status 201), aber das danach?
    }
    


}
