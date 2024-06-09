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
    private MyHash<String, Artist> songArtists;

    // hacer constructor, getters y setters
}
