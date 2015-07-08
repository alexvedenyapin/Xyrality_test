package com.alex.xyrality_test.xyrality_test.rest.results;

public class World {
    private String id;
    private String language;
    private String url;
    private String country;
    private WorldStatus status;
    private String mapURL;
    private String name;

    public World(String id, String language, String url, String country, WorldStatus status, String mapURL, String name) {
        this.id = id;
        this.language = language;
        this.url = url;
        this.country = country;
        this.status = status;
        this.mapURL = mapURL;
        this.name = name;
    }

    class WorldStatus {
        String id;
        String description;

        public WorldStatus(String id, String description) {
            this.id = id;
            this.description = description;
        }
    }
}
