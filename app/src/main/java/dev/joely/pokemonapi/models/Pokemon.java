package dev.joely.pokemonapi.models;

public class Pokemon {
    private String name;
    private String imageUrl;  // Replace with actual field from API

    public Pokemon(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
