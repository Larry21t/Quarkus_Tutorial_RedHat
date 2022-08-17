package com.redhat.developers;

public class FruitDTO {
    private String name;

    private String season;

    private double carbohydrates;

    private double calories;

    private double sugar;

    private FruitDTO(String name, String season, double carbohydrates, double calories, double sugar){ //privater Konstruktor
        this.name = name;
        this.season = season;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.sugar = sugar;
    }

    public static FruitDTO of(Fruit fruit, FruityVice fruityVice) { //Was passiert hier beim of?
        return new FruitDTO( //Das hier ruft den obigen privaten Konstruktor auf
            fruit.name, 
            fruit.season, 
            fruityVice.getNutritions().getCarbohydrates(), 
            fruityVice.getNutritions().getCalories(),
            fruityVice.getNutritions().getSugar());
    }

    public String getName(){
        return name;
    }

    public String getSeason() {
        return season;
    }

    public double getCarbohydrates(){
        return carbohydrates;
    }

    public double getCalories(){
        return calories;
    }

    public double getSugar(){
        return sugar;
    }

}
