package com.zam.myinternship.model;

public class CountryInfo {
    private String name, capital, population;

    public CountryInfo(String name, String capital, String population) {
        this.name = name;
        this.capital = capital;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getPopulation() {
        return population;
    }
}
