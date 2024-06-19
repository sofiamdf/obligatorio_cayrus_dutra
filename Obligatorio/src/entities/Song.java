package entities;

import uy.edu.um.prog2.adt.hash.MyHash;

import java.time.LocalDate;

public class Song {
    private String spotifyId;
    private String name;
    private boolean isExplicit;
    private int durationMs;
    private String albumName;
    private LocalDate albumReleaseDate;
    private double danceability;
    private double energy;
    private int key;
    private double loudness;
    private double mode;
    private double speechiness;
    private double acousticness;
    private double instrumentalness;
    private double liveness;
    private double valence;
    private double tempo;
    private int timeSignature;
    private String[] artista;
    private int counter = 0;
    // hacer constructor, getters y setters

    public Song(String name) {
        this.name = name;
    }

    public Song(String name, String artist) {
        this.name = name;
        this.artista = artist.split(",");
    }

    public void increaseCounter(){
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
