package com.redhat.developers;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
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

@Path("/fruit") //Legt den URL-Pfad(-endung) fest, zu welchem die Ressource gehört bzw. sie abgerufen werden kann.
public class FruitResource {
    //FruitRepository fruitRepository;

    @RestClient //Kennzeichnet etwas als REST-Client, also als einer der drei Komponenten vom REST (Client-Applikation, API, Web-Server)
    FruityViceService fruityViceService;

  /*public FruitResource(FruitRepository fruitRepository){ //Konstruktor
        this.fruitRepository = fruitRepository;
    }*/

    // @GET //Die Methode mit diesem Dekorator kann HTTP-GET-Requests generieren, also vom Server eine Ressource abrufen
    // @Produces(MediaType.APPLICATION_JSON) //Definiert Medientyp, welchen die Ressource erstellen kann und als Antwort an Client schickt. 
    // public List<Fruit> fruits(@QueryParam("season") String season, @QueryParam("name") String name) { //@QueryParam = Parameter aus Query(?season=Summer) in der URL 
    //     if(name != null){ 
    //         Log.infof("Searching for %s", name); //Wo wird das protokolliert? -> Wenn man im Development-Modus ist im Terminal
    //         return fruitRepository.findByName(name);
    //     }
    //     if(season != null){
    //         Log.infof("Searching for %s fruits", season);
    //         return fruitRepository.findBySeason(season);
    //     }
    //     return Fruit.listAll();
    // }



    @GET//Die folgende Funktion "getFruit" habe ich selber hinzugefügt -> nicht im Tutorial, ist nicht so schön gelöst, da es nun 2 Get-Annotations gibt
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FruitDTO> getFruit(@PathParam("name") String name){ //Der URL kann nach /fruits noch einen Früchtenamen(z.B. /Apple) angehängt werden und dann wird die spezifische Frucht angzeigt. 
        if(name != null){                                        
            return Fruit.findByName(name).stream()
                    .map(fruit -> FruitDTO.of(fruit, fruityViceService.getFruitByName(fruit.name)))
                    .collect(Collectors.toList());
        }
        return Fruit.<Fruit>listAll().stream()
                .map(fruit -> FruitDTO.of(fruit, fruityViceService.getFruitByName(fruit.name)))
                .collect(Collectors.toList()); 
    }



    @Transactional //Kennzeichnet Übertragung von Daten
    @POST //Diese Methode mit dem Dekorator, kann HTTP-Post-Requests generieren
    @Consumes(MediaType.APPLICATION_JSON) //Definiert welchen Medientyp der Server akzeptiert
    @Produces(MediaType.APPLICATION_JSON)
    public Response newFruit(Fruit fruit) {
        fruit.id = null;
        fruit.persist(); //Tut den Datensatz in die Datenbank
        return Response.status(Status.CREATED).entity(fruit).build(); //Was macht diese Zeile genau? Status zurückgeben? -> der Status ist created (HTTP_Status 201), aber das danach?
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FruitDTO> fruits(@QueryParam("season") String season) {
        if (season != null) {
            return Fruit.findBySeason(season).stream() //Alle Früchte, die die bestimmte Saison haben, werden mit den Infos aufgelistet
                .map(fruit -> FruitDTO.of(fruit, fruityViceService.getFruitByName(fruit.name)))
                .collect(Collectors.toList()); //Damit ein Stream zu einer Liste umgewandelt wird und als Liste zurückgegeben wird. 
        }
        return Fruit.<Fruit>listAll().stream() //Wenn keine Saison angegeben wird, dann werden alle Früchte mit den Infos aufgelistet. 
                .map(fruit -> FruitDTO.of(fruit, fruityViceService.getFruitByName(fruit.name))) //Was macht of genau?
                .collect(Collectors.toList()); 
    }
    


}
