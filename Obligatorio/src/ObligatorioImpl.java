import uy.edu.um.prog2.adt.hash.*;
import entities.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

public class ObligatorioImpl<K,V>{

//    private MyHash<String, Song> CSVsongs;
//
//    public ObligatorioImpl() {
//        this.CSVsongs = CSVsongs;
//    }

    public MyHash<K,V> CreateHash(String[] key,String[] value) {
        MyHash<K,V> songHash = new MyHashImpl<K, V>();
        BufferedReader br = null;

        String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
        String path2 = "\"C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv\"";

        try {
            try {
                br = new BufferedReader(new FileReader(path1));
            } catch (FileNotFoundException e1) {
                br = new BufferedReader(new FileReader(path2));
            }

            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                for (int i = 0; i < data.length; i++) {
                    // Remove double quotes from each element
                    data[i] = data[i].replace("\"", "");
                }
                songHash.put(data[k], data[v]);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null; // puse esto para que no de error por mientras
    }

    public void Top10(String country, LocalDate date){
        MyHash<String, Song> songs =  new MyHashImpl<>();
        for (int i = 0; i < CSVsongs.size(); i++) {
            // no tenemos metodo size en hashhhhhhhh no se que hacer
            Song song = CSVsongs.get(i);
            if (song.getCountry().equals(country) // && aca queria comparar las fechas pero no se como porque seria la fecha del daily rank que no tenemos todavia
                // tampoco puedo hacer los getters por el puto code with me
        }
    }
}

/*
public class ObligatorioImpl<K, V> {

    private MyHash<K, V> songHash;
    private String path1 = "/path/to/first/file.csv"; // reemplaza con tu ruta
    private String path2 = "/path/to/second/file.csv"; // reemplaza con tu ruta

    public ObligatorioImpl() {
        this.songHash = new MyHashImpl<>(); // Asegúrate de que MyHashImpl implementa MyHash
    }

    public void createHash() {
        BufferedReader br = null;

        try {
            try {
                br = new BufferedReader(new FileReader(path1));
            } catch (FileNotFoundException e1) {
                br = new BufferedReader(new FileReader(path2));
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                for (int i = 0; i < data.length; i++) {
                    // Remove double quotes from each element
                    data[i] = data[i].replace("\"", "");
                }
                // Assuming data[0] is the unique identifier for each song
                songHash.put((K) data[0], (V) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ObligatorioImpl<String, String[]> obligatorio = new ObligatorioImpl<>();
        obligatorio.createHash();
    }
}

// Define your MyHash and MyHashImpl
interface MyHash<K, V> {
    void put(K key, V value);
    V get(K key);
}

class MyHashImpl<K, V> implements MyHash<K, V> {
    private Map<K, V> map;

    public MyHashImpl() {
        this.map = new HashMap<>();
    }

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }
}

 */