package com.redhat.developers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController //Kennzeichnet einen Controller, der REST Funktionen anbietet.
@RequestMapping(path = "/spring-fruit") //Ordnet einen bestimmten Pfad einem Controller zu.
public class FruitController { 
    private SpringFruitRepository fruitRepository;

    public FruitController(SpringFruitRepository fruitRepository){
        this.fruitRepository = fruitRepository;
    }

    @GetMapping //Wie @GET aber für Spring
    public List<Fruit> fruits(@RequestParam("season") String season1, @RequestParam("name") String name1){ //Parameter season aus dem Web wird an den Methodenparameter season1 gebunden
        if(name1 != null){
            return fruitRepository.findByName(name1);  
        }   
        if(season1 != null){
            return fruitRepository.findBySeason(season1); //Wo ist die Definition von findBySeason? Denn das SpringFruitRepository fruitRepository ist ja 
        }                                               // nur ein Interface mit der abstrakten Methode "findBySeason", wieso filtert er die Früchte trotzdem richtig nach Saison?
        return fruitRepository.findAll(); //Wieso nicht Fruit.listAll()?
    }

    @GetMapping//Die folgende Funktion "getFruit" habe ich selber hinzugefügt -> nicht im Tutorial
    @RequestMapping(path = "/{name}") //Es kann in der URL nach /fruits noch einen Früchtenamen (z.B. /Apple) angehängt werden und dann wird die spezifische Frucht angzeigt. 
    public List<Fruit> getFruit(@PathVariable("name") String name){ //@PathVariable ist wie @PathParam einfach für Spring
        if(name != null){ //Dieses if braucht es eigenltich hier nicht oder? Es würde reichen einfach return fruitRepository.findByName(name);
            return fruitRepository.findByName(name);
        }
        return Fruit.listAll();
    }

}
