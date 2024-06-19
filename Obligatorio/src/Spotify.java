import entities.Artist;
import entities.DateRange;
import entities.Song;
import uy.edu.um.prog2.adt.binarytree.MySearchBinaryTree;
import uy.edu.um.prog2.adt.binarytree.MySearchBinaryTreeImpl;
import uy.edu.um.prog2.adt.hash.*;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.linkedlist.MyList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

public class Spotify {
    // 1er reporte
    MyHash<String, Artist> artists = new MyHashImpl<>();
    MySearchBinaryTree<Integer, String> top7ArtistTree = new MySearchBinaryTreeImpl<>();
    MySearchBinaryTree<String, ArrayList<String>> top10songTree = new MySearchBinaryTreeImpl<>();
    MyList<String> top5List = new MyLinkedListImpl<>();
    MyList<Song> canciones = new MyLinkedListImpl<>();
//    public static class Song {
//        String name;
//        String artist;
//        int position;
//    }
//
//    public Song(String name, String artist, int position) {
//        this.name = name;
//        this.artist = artist;
//        this.position = position;
//    }

//    public void CreateHashTop10() {
//
//        BufferedReader br = null;
//
//        String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
//        String path2 = "\"C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv\"";
//
//        try {
//            try {
//                br = new BufferedReader(new FileReader(path1));
//            } catch (FileNotFoundException e1) {
//                br = new BufferedReader(new FileReader(path2));
//            }
//
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                line = line.replace("\"\"", "\"").replaceAll("^\"|\"$", "");
//                String[] data = line.split(",\"");
//                String[] keys = new String[2];
//                String[] values = new String[25];
//                for (int i = 0; i < 24; i++) {
//                    // Remove double quotes from each element
//                    data[i] = data[i].replace("\"", "");
//                    values[i] = data[i];
//                }
//
//                keys[0] = data[6];
//                keys[1] = data[7];
//                Top10songHash.put(keys, values);
//            }
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public ArrayList[] Top10(MyList<String[]> values){
//        ArrayList[] top = new ArrayList[10];
//        for (int i = 0; i < values.size(); i++) {
//            String[] current = values.get(i);
//            current[2]
//
//        }
//    }


    public MySearchBinaryTree<String, ArrayList<String>> Top10tree(String pais, String date) throws FileNotFoundException {
        String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
        String path2 = "\"C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv\"";

        try {
            BufferedReader br;

            try {
                br = new BufferedReader(new FileReader(path1));
            } catch (FileNotFoundException e1) {
                br = new BufferedReader(new FileReader(path2));
            }

            String line = "";
            while ((line = br.readLine()) != null) {
                line = line.replace("\"\"", "\"").replaceAll("^\"|\"$", "");
                String[] data = line.split(",\"");
                for (int i = 0; i < data.length; i++) {
                    // Remove double quotes from each element
                    data[i] = data[i].replace("\"", "");
                }
                if (data[6].equals(pais) && data[7].equals(date)) {
                    ArrayList<String> list = new ArrayList();
                    list.add(data[1]);
                    list.add(data[2]);
                    list.add(data[3]);
                    top10songTree.add(data[3], list);
                }
            }
            return top10songTree;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printTop10Songs(MySearchBinaryTree<String, ArrayList<String>> tree) {
        MyList<ArrayList<String>> inOrderList = tree.inOrderWithValues();
        for (int i = 0; i < inOrderList.size(); i++) {
            ArrayList<String> song = inOrderList.get(i);
            System.out.println("Puesto: " + song.get(2) + ", Canción: " + song.get(0) + ", Artista: " + song.get(1));
        }
    }

    // 2do reporte

//    public MyList<String> top5MostFrequentSongs(String date) throws FileNotFoundException, EntidadNoExiste {
//        MyHash<String, Integer> songFrequency = new MyHashImpl<String, Integer>();
//        String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
//        String path2 = "C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv";
//
//        try (BufferedReader br = new BufferedReader(new FileReader(path1))) {
//            String line;
    public void OrderTop10(String country, String date) throws FileNotFoundException {
//             String[] key = {country, date.toString()};
//             MyList<String[]> Songs = Top10songHash.get(key);
//             top10songTree.

        MySearchBinaryTree top10 = Top10tree(country, date);
        for (int i = 1; i <= 10; i++) {
            ArrayList<String> songs = (ArrayList<String>) top10.find(String.valueOf(i));
            if (songs != null) {
                System.out.println("Top " + i + ": " + songs);
            } else {
                System.out.println("Top " + i + " not found.");
            }
        }
    }


//    public MyHash<String, String> Top5Hash(String date) throws FileNotFoundException {
//        String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
//        String path2 = "\"C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv\"";
//        ArrayList<String> list = new ArrayList();
//
//        try {
//            BufferedReader br;
//
//            try {
//                br = new BufferedReader(new FileReader(path1));
//            } catch (FileNotFoundException e1) {
//                br = new BufferedReader(new FileReader(path2));
//            }
//
//            String line = "";
//            while ((line = br.readLine()) != null) {
//                line = line.replace("\"\"", "\"").replaceAll("^\"|\"$", "");
//                String[] data = line.split(",\"");
//                for (int i = 0; i < data.length; i++) {
//                    data[i] = data[i].replace("\"", "");
//                }
//                if (data[7].equals(date)) {
//                    String songName = data[1];
//                    if (songFrequency.contains(songName)) {
//                        MyList<Integer> frequencies = songFrequency.get(songName);
//                        frequencies.add(0, frequencies.remove(0) + 1);
//                    } else {
//                        MyList<Integer> frequencies = new MyLinkedListImpl<>();
//                        frequencies.add(1);
//                        songFrequency.put(songName, frequencies);
//                    }
//                }
//            }

//                    if(list.contains(data[1])) {
//                        list.get(data[1])
//                    }
//                    list.add(data[1]);
//                    top5List.put(data[1], data[1]);
//                    int count = top5List.count(data[1]);
//
//                }
//            }
//            return top5List;

//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//
//        MyList<String> top5Songs = new MyLinkedListImpl<>();
//        for (int i = 0; i < 5; i++) {
//            String mostFrequentSong = findMostFrequentSong(songFrequency);
//            if (mostFrequentSong != null) {
//                top5Songs.add(mostFrequentSong);
//                songFrequency.remove(mostFrequentSong);
//            }
//        }
//
//        return top5Songs;
//    }
//
//    private String findMostFrequentSong(MyHash<String, MyList<Integer>> songFrequency) {
//        String mostFrequentSong = null;
//        int maxFrequency = 0;
//        for (String song : songFrequency.keys()) {
//            MyList<Integer> frequencies = songFrequency.get(song);
//            int frequency = frequencies.get(0);  // Assuming the first element is the frequency
//            if (frequency > maxFrequency) {
//                mostFrequentSong = song;
//                maxFrequency = frequency;
//            }
//        }
//        return mostFrequentSong;
//    }
//
//    public void Ordertop5(String date) throws FileNotFoundException {
//        MyHash list = Top5Hash(date);
//
//
//    }


//    public void InsertSongsToArtists(DateRange Dates){
//            String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
//            String path2 = "\"C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv\"";
//
//
//            try {
//                BufferedReader br;
//
//                try {
//                    br = new BufferedReader(new FileReader(path1));
//                } catch (FileNotFoundException e1) {
//                    br = new BufferedReader(new FileReader(path2));
//                }
//
//                String line = "";
//                while ((line = br.readLine()) != null) {
//                    line = line.replace("\"\"", "\"").replaceAll("^\"|\"$", "");
//                    String[] data = line.split(",\"");
//                    for (int i = 0; i < data.length; i++) {
//                        // Remove double quotes from each element
//                        data[i] = data[i].replace("\"", "");
//                    }
//                    if (data[7].in(dates)) {
//                        if (data[2] is String[]){
//                            //separate multiple artist into indiviual names
//                            String[] [data[2]] =line.split(",\"");
//                            for (int i = 0; i < data[2].length; i++) {
//                                Artist newArtist = new Artist([data[2]][i]);
//                            }
//                        }
//                        else(Artist newArtist = new Artist(data[2]))
//                        Song newSong = new Song(data[1]);
//                        newArtist.artistSongs.add(Song);
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }

    public void insertSongsToArtists(DateRange dates) {

//        String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
//        String path2 = "C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv";
//        try {
//            BufferedReader br;
//
//            try {
//                br = new BufferedReader(new FileReader(path1));
//            } catch (FileNotFoundException e1) {
//                br = new BufferedReader(new FileReader(path2));
//            }
//
//             String line = br.readLine();
//            while ((line = br.readLine()) != null) {
//                int fixescomma = 7;
//                line = line.replace("\"\"", "\"").replaceAll("^\"|\"$", "");
//                String[] data = line.split(",\"");
//                for (int i = 0; i < data.length; i++) {
//                    data[i] = data[i].replace("\"", "");
//                }
//                if (data.length > 25){
//                    fixescomma++;
//                }
//                LocalDate songDate = LocalDate.parse(data[fixescomma]);  // Assuming data[7] is the date
//                if (dates.contains(songDate)) {
//                    String[] artistNames = data[2].split(", ");  // Assuming data[2] is the artist field
//                    for (String artistName : artistNames) {
//                        Artist artist = artists.getValue(artistName);
//                        if (artist == null) {
//                            Artist newArtist = new Artist(artistName);
//                            artists.put(artistName, newArtist);
//                        } else {
//                            artist.increaseCounter();
//                        }
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //}
        String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
        String path2 = "C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(path1))) {
            insertArtists(br, dates);
        } catch (FileNotFoundException e) {
            try (BufferedReader br = new BufferedReader(new FileReader(path2))) {
                insertArtists(br, dates);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void insertArtists(BufferedReader br, DateRange dates) throws IOException {
        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            line = line.replace("\"\"", "\"").replaceAll("^\"|\"$", "");
            String[] data = line.split(",\"");
            if (data.length > 25) {
                data[7] = data[8];
            }
            data[7] = data[7].replace("\"", "");
            LocalDate songDate = LocalDate.parse(data[7]);

            if (dates.contains(songDate)) {
                String[] artistNames = data[2].split(", ");
                for (String name : artistNames) {
                    String artistName = name.replaceAll("\"$", "");
                    Artist artist = artists.getValue(artistName);
                    if (artist == null) {
                        Artist newArtist = new Artist(artistName);
                        artists.put(artistName, newArtist);
                        newArtist.increaseCounter();
                    } else {
                        artist.increaseCounter();
                    }
                }
            }
        }
    }


    public Artist findOrCreateArtist(String artistName) {
        Artist artist = artists.getValue(artistName);
        if (artist == null) {
            Artist newArtist = new Artist(artistName);
            artists.put(artistName, newArtist);
            return newArtist;
        } else {
            artist.increaseCounter();
            return artist;
    }
    }


    public void getTop7Artists(DateRange dates) {
        insertSongsToArtists(dates);
        for (int i = 0; i < artists.getSize(); i++) {
            Artist artist = artists.getIndex(i);
            if (artist != null) {
                top7ArtistTree.add(artist.getCounter(), artist.getName());
            }
        }
        MyList<String> orderdList = top7ArtistTree.rightRootLeftTraversal();
        for (int i = 0; i <= 7; i++) {
            String artist = orderdList.get(i);
            if (artist != null) {
                System.out.println("Top " + (i + 1) + ": " + artist);
            } else {
                System.out.println("Top " + i + " not found.");
            }
        }
    }



    public void getArtistData(LocalDate date, String nameArtist){

        String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
        String path2 = "C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(path1))) {
            getCountArtist(br, date, nameArtist);
        } catch (FileNotFoundException e) {
            try (BufferedReader br = new BufferedReader(new FileReader(path2))) {
                getCountArtist(br, date, nameArtist);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getCountArtist(BufferedReader br, LocalDate date, String nameArtist) throws IOException {
        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            line = line.replace("\"\"", "\"").replaceAll("^\"|\"$", "");
            String[] data = line.split(",\"");
            if (data.length > 25) {
                data[7] = data[8];
            }
            data[7] = data[7].replace("\"", "");
            LocalDate songDate = LocalDate.parse(data[7]);

            if (date.equals(songDate)) {
                String[] artistNames = data[2].split(", ");
                for (String name : artistNames) {
                    String artistName = name.replaceAll("\"$", "");
                    if (artistName.equals(nameArtist)) {
                        Artist artist = findOrCreateArtist(nameArtist);
                        artist.increaseCounter();
                    }
                    }
                }
            }
        Artist thisArtist = findOrCreateArtist(nameArtist);
        if(thisArtist == null) {
            throw new FileNotFoundException();
        }
        System.out.println(thisArtist.getName() + " had " + thisArtist.getCounter() + " songs in the top50 on " + date.toString());
    }
//        String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
//        String path2 = "C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv";
//
//                try {
//                    BufferedReader br;
//
//                    try {
//                        br = new BufferedReader(new FileReader(path1));
//                    } catch (FileNotFoundException e1) {
//                        br = new BufferedReader(new FileReader(path2));
//                    }
//
//                    String line = br.readLine();
//                    Artist artist = new Artist(nameArtist);
//                    while ((line = br.readLine()) != null) {
//                        line = line.replace("\"\"", "\"").replaceAll("^\"|\"$", "");
//                        String[] data = line.split(",\"");
//                        for (int i = 0; i < data.length; i++) {
//                            data[i] = data[i].replace("\"", "");
//                        }
//                        if (data[7].equals(date)) {
//                            String[] artistNames = data[2].split(", ");  // Assuming data[2] is the artist field
//                            for (String artistName : artistNames) {
//                                if (artistName.equals(nameArtist)) {
//                                    artist.increaseCounter();
//                                }
//                            }
//                        }
//                    }
//                    System.out.println(artist.getCounter());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }

    public void insertSongs(DateRange dates) {
        String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
        String path2 = "C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv";

        try {
            BufferedReader br;

            try {
                br = new BufferedReader(new FileReader(path1));
            } catch (FileNotFoundException e1) {
                br = new BufferedReader(new FileReader(path2));
            }

            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                line = line.replace("\"\"", "\"").replaceAll("^\"|\"$", "");
                String[] data = line.split(",\"");
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replace("\"", "");
                }
                LocalDate songDate = LocalDate.parse(data[7]);  // Assuming data[7] is the date
                if (dates.contains(songDate)) {
                    String[] artistNames = data[2].split(", ");  // Assuming data[2] is the artist field

                    for (String artistName : artistNames) {
                        // Create a new Artist object or retrieve existing one if available
                        Artist artist = findOrCreateArtist(artistName);
                        artist.increaseCounter();
                        // Output for verification (print or further processing)
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



















}



