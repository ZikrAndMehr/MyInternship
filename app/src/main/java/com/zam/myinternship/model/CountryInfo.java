package com.zam.myinternship.model;

import java.util.List;

public class CountryInfo {
    private String name, capital;
    private long population;
    private List<Language> languages;


    public CountryInfo(String name, String capital, Long population, List<Language> languages) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.languages=languages;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public Long getPopulation() {
        return population;
    }

    public List<Language> getLanguage() {
        return languages;
    }

    public static class Language {
        private String iso639_1;
        private String iso639_2;
        private String name;
        private String nativeName;

        public Language(String iso639_1, String iso639_2, String name, String nativeName) {
            this.iso639_1 = iso639_1;
            this.iso639_2 = iso639_2;
            this.name = name;
            this.nativeName = nativeName;
        }

        public String getIso6391() {
            return iso639_1;
        }

        public String getIso6392() {
            return iso639_2;
        }

        public String getName() {
            return name;
        }

        public String getNativeName() {
            return nativeName;
        }
    }
}
