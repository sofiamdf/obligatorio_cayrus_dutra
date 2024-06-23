package entities;

import uy.edu.um.prog2.adt.hash.MyHash;

import java.time.LocalDate;

public class Song {
    private String name;
    private int counter = 0;

    public Song(String name) {
        this.name = name;
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
