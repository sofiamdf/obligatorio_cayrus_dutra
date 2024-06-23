package entities;

import uy.edu.um.prog2.adt.hash.MyHash;

import java.util.ArrayList;

public class Artist {
    private String name;
    private int counter = 0;


    public void increaseCounter(){
        counter++;
    }

    public Artist(String nameParam) {
        name = nameParam;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return name.equals(artist.name);
    }

}
