package com.redhat.developers;

import javax.json.bind.annotation.JsonbCreator;

public class FruityVice {
    
    private String name;

    private Nutritions nutritions;

    FruityVice(String name, Nutritions nutrions) { //Konstruktor
        this.name = name;
        this.nutritions = nutrions;
    }

    @JsonbCreator //Wenn eine Instanz dieser Klasse erzeugt wird, dann wird automatisch die Methode mit dieser Annotation aufgerufen. Für was braucht es diesen Konstruktor, reicht nicht der Normale hier oben?
    public static FruityVice of(String name, Nutritions nutritions){ //Was bedeutet das of() hier? //Was macht diese Funktion genau bzw. was nützt sie?
        return new FruityVice(name, nutritions);
    }

    public String getName(){ //Getter
        return name;
    }

    public Nutritions getNutritions(){ //Getter
        return nutritions;
    }

    public static class Nutritions{ //nur die Klasse FruityVice braucht die Nutritions Klasse, deshalb wird sie direkt in der Klasse FruityVice definiert -> nested classes

        private double carbohydrates;

        private double calories;

        private double sugar;

        Nutritions(double carbohydrates, double calories, double sugar){ //Konstruktor
            this.carbohydrates = carbohydrates;
            this.calories = calories;
            this.sugar = sugar;
        }

        @JsonbCreator
        public static Nutritions of(double carbohydrates, double calories, double sugar) {  //Für was braucht es diesen Konstruktor, reicht nicht der Normale hier oben?
            return new Nutritions(carbohydrates, calories, sugar);
        }

        public double getCarbohydrates(){ //Getter
            return carbohydrates;
        }

        public double getCalories(){ //Getter
            return calories;
        }

        public double getSugar(){ //Getter
            return sugar;
        }
    }
}



