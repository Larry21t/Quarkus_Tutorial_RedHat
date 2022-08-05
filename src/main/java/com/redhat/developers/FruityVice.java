package com.redhat.developers;

import javax.json.bind.annotation.JsonbCreator;

public class FruityVice {
    
    private String name;

    private Nutritions nutritions;

    FruityVice(String name, Nutritions nutrions) { //Konstruktor
        this.name = name;
        this.nutritions = nutrions;
    }

    @JsonbCreator //Wenn eine Instanz dieser Klasse erzeugt wird, dann wird automatisch die Methode mit dieser Annotation aufgerufen. 
    public static FruityVice of(String name, Nutritions nutritions){ //Was bedeutet das of() hier? //Was macht diese Funktion genau bzw. was nützt sie?
        return new FruityVice(name, nutritions);
    }

    public String getName(){ //Getter
        return name;
    }

    public Nutritions getNutritions(){ //Getter
        return nutritions;
    }

    public static class Nutritions{

        private double carbohydrates;

        private double calories;

        Nutritions(double carbohydrates, double calories){ //Konstruktor
            this.carbohydrates = carbohydrates;
            this.calories = calories;
        }

        @JsonbCreator
        public static Nutritions of(double carbohydrates, double calories) {
            return new Nutritions(carbohydrates, calories);
        }

        public double getCarbohydrates(){ //Getter
            return carbohydrates;
        }

        public double getCalories(){ //Getter
            return calories;
        }
    }
}



