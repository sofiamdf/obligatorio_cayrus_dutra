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

public class Spotify {

    MyHash<String, Artist> artists = new MyHashImpl<>();
    MySearchBinaryTree<Integer, String> top7ArtistTree = new MySearchBinaryTreeImpl<>();
    MySearchBinaryTree<String, ArrayList<String>> top10songTree = new MySearchBinaryTreeImpl<>();
    MySearchBinaryTree<Integer, String> top5SongTree = new MySearchBinaryTreeImpl<>();
    MyHash<String, Song> songs = new MyHashImpl<>();

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


//     1) Top 10 songs en un país en un día dado. Este reporte debe incluir el nombre de
//    la canción, el artista, y en qué puesto se encuentra en el top. Las songs deben
//    estar ordenadas de manera descendente. El día será ingresado en el formato
//    YYYY-MM-DD.

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
                    ArrayList<String> list = new ArrayList<>();
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


    public void OrderTop10(String country, String date) throws FileNotFoundException {

        MySearchBinaryTree<String, ArrayList<String>> top10 = Top10tree(country, date);
        MyList<ArrayList<String>> inOrderList = top10.inOrderWithValues();
        for (int i = 0; i < 10; i++) {
            ArrayList<String> song = inOrderList.get(i);
            System.out.println("Puesto: " + song.get(2) + ", Canción: " + song.get(0) + ", Artista: " + song.get(1));
        }
//        for (int i = 1; i <= 10; i++) {
//            ArrayList<String> songs = top10.find(String.valueOf(i));
//            if (songs != null) {
//                System.out.println("Top " + i + ": " + songs);
//            } else {
//                System.out.println("Top " + i + " not found.");
//            }
//        }
    }


    // 2) Top 5 songs que aparecen en más top 50 en un día dado. Las songs deben
    //estar ordenadas de manera descendente. Se espera que esta operación sea de
    //orden n en notación Big O.

    public void getSongData(LocalDate date){

        String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
        String path2 = "C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(path1))) {
            getCountSongs(br, date);
        } catch (FileNotFoundException e) {
            try (BufferedReader br = new BufferedReader(new FileReader(path2))) {
                getCountSongs(br, date);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getCountSongs(BufferedReader br, LocalDate date) throws IOException {
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
                String song = data[1];
                String newSongName = song.replaceAll("\"$", "");
                Song newSong = songs.getValue(newSongName);
                if (newSong == null) {
                    Song addSong = new Song(newSongName);
                    songs.put(newSongName, addSong);
                    addSong.increaseCounter();
                } else {
                    newSong.increaseCounter();
                }
            }
        }

    }

    public void getTop5Songs(LocalDate date) {
        getSongData(date);
        for (int i = 0; i < songs.getSize(); i++) {
            Song song = songs.getIndex(i);
            if (song != null) {
                top5SongTree.add(song.getCounter(), song.getName());
            }
        }
        MyList<String> orderdList = top5SongTree.rightRootLeftTraversal();
        for (int i = 0; i < 5; i++) {
            String songname = orderdList.get(i);
            if (songname != null) {
                System.out.println("Top " + (i + 1) + " song of " + date.toString() + " : " + songname);
            } else {
                System.out.println("Top " + i + " not found.");
            }
        }
    }



    // 3) Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado. Cada
    //aparición (como cada canción) distinta debe contarse, y se debe separar las
    //songs que tengan más de un artista contabilizando una aparición para cada uno.
    //Si un artista tiene 10 songs en el top 50, deben contabilizarse 10 ocurrencias.

    public void insertSongsToArtists(DateRange dates) {


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
        for (int i = 0; i < 7; i++) {
            String artist = orderdList.get(i);
            if (artist != null) {
                System.out.println("Top " + (i + 1) + ": " + artist);
            } else {
                System.out.println("Top " + i + " not found.");
            }
        }
    }


    //4) Cantidad de veces que aparece un artista específico en un top 50 en una fecha
    //dada.

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


//    5) Cantidad de songs con un tempo en un rango específico para un rango
//específico de fechas.





















}



