package com.redhat.developers;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class FruitRepository implements PanacheRepository<Fruit>{
    public List<Fruit> findBySeason(String season) {
        return find("upper(season)", season.toUpperCase()).list(); //upper(season) schreibt in der DB alle Namen gross und mit season.toUpperCase()
    }                                      //wird die Saison, die der User eingegeben hat, auch gross geschrieben. Somit wird die Saison sicher gefunden, egal ob der User sie klein oder gross schreibt. 
    public List<Fruit> findByName(String name) {
        return find("upper(name)", name.toUpperCase()).list(); 
    } 
}
