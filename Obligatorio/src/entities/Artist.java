package entities;

import uy.edu.um.prog2.adt.hash.MyHash;

import java.util.ArrayList;

public class Artist {
    private String name;
    private ArrayList<Song> artistSongs;
    private int counter = 0;


    public void increaseCounter(){
        counter++;
    }

    public Artist(String name) {
        this.name = name;
    }

    public ArrayList<Song> getArtistSongs() {
        return artistSongs;
    }

    public void setArtistSongs(ArrayList<Song> artistSongs) {
        this.artistSongs = artistSongs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
