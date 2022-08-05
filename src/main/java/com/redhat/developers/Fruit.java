package com.redhat.developers;

import java.util.List;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity //Kennzeichnet die Klasse als Entität
public class Fruit extends PanacheEntity {
    public String name;
    public String season;

    public static List<Fruit> findBySeason(String season){ //Diese Funktion braucht man hier eigentlich nicht mehr, da man auf die Funktion im FruitRepository zugreift.
        return find("season", season).list(); //Gibt Liste mit den Früchten, die die bestimmte Saison haben zurück.
    }

    public static List<Fruit> findByName(String name){ //Diese Funktion braucht man hier eigentlich nicht mehr, da man auf die Funktion im FruitRepository zugreift.
        return find("name", name).list();
    }
}
