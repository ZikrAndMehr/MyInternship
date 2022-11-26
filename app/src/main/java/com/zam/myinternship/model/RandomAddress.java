package com.zam.myinternship.model;

public class RandomAddress {

    private int id;
    private String uid, city, street_name, street_address, secondary_address, building_number, mail_box, community,
            zip_code, zip, postcode, time_zone, street_suffix, city_suffix, city_prefix, state, state_abbr, country,
            country_code, latitude, longitude, full_address;

    public RandomAddress(int id, String uid, String city, String street_name, String street_address,
                         String secondary_address, String building_number, String mail_box, String community,
                         String zip_code, String zip, String postcode, String time_zone, String street_suffix,
                         String city_suffix, String city_prefix, String state, String state_abbr, String country,
                         String country_code, String latitude, String longitude, String full_address) {
        this.id = id;
        this.uid = uid;
        this.city = city;
        this.street_name = street_name;
        this.street_address = street_address;
        this.secondary_address = secondary_address;
        this.building_number = building_number;
        this.mail_box = mail_box;
        this.community = community;
        this.zip_code = zip_code;
        this.zip = zip;
        this.postcode = postcode;
        this.time_zone = time_zone;
        this.street_suffix = street_suffix;
        this.city_suffix = city_suffix;
        this.city_prefix = city_prefix;
        this.state = state;
        this.state_abbr = state_abbr;
        this.country = country;
        this.country_code = country_code;
        this.latitude = latitude;
        this.longitude = longitude;
        this.full_address = full_address;
    }

    public String getCountry() {
        return country;
    }
}
