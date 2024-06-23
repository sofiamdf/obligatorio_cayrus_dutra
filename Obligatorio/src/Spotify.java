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

    /* 1er reporte
        Top 10 canciones en un país en un día dado. Este reporte debe incluir el nombre de
        la canción, el artista, y en qué puesto se encuentra en el top. Las canciones deben
        estar ordenadas de manera descendente. El día será ingresado en el formato YYYY-MM-DD.
     */

    public MySearchBinaryTree<Integer, ArrayList<String>> Top10tree(String pais, String date) throws FileNotFoundException {
        MySearchBinaryTree<Integer, ArrayList<String>> top10songTree = new MySearchBinaryTreeImpl<>();
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
                String currentCountry = data[6];
                String currentDate = data[7];
                if (currentCountry.equals(pais) && currentDate.equals(date) && Integer.parseInt(data[3]) <= 10) {
                    ArrayList<String> list = new ArrayList();
                    list.add(data[1]);
                    list.add(data[2]);
                    list.add(data[3]);
                    top10songTree.add(Integer.parseInt(data[3]), list);
                }
            }
            return top10songTree;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printTop10Songs(MySearchBinaryTree<Integer, ArrayList<String>> tree) {
        MyList<ArrayList<String>> inOrderList = tree.inOrderWithValues();
        for (int i = 0; i <= 9; i++) {
            ArrayList<String> song = inOrderList.get(i);
            System.out.println("Puesto: " + song.get(2) + ", Canción: " + song.get(0) + ", Artista: " + song.get(1));
        }
    }

    public void OrderTop10(String country, String date) throws FileNotFoundException {
        MySearchBinaryTree<Integer, ArrayList<String>> top10 = Top10tree(country, date);
        MyList<ArrayList<String>> inOrderList = top10.inOrderWithValues();
        for (int i = 0; i <= 9; i++) {
            ArrayList<String> song = inOrderList.get(i);
            System.out.println("Puesto: " + song.get(2) + ", Canción: " + song.get(0) + ", Artista: " + song.get(1));
        }
    }

    /* 2do reporte
        Top 5 canciones que aparecen en más top 50 en un día dado. Las canciones deben
        estar ordenadas de manera descendente. Se espera que esta operación sea de
        orden n en notación Big O.
     */

    public MyHash<String, Song> getSongData(LocalDate date) {
        String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
        String path2 = "C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path1))) {
            return getCountSongs(br, date);
        } catch (FileNotFoundException e) {
            try (BufferedReader br = new BufferedReader(new FileReader(path2))) {
                return getCountSongs(br, date);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private MyHash<String, Song> getCountSongs(BufferedReader br, LocalDate date) throws IOException {
        MyHash<String, Song> songs = new MyHashImpl<>();
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
        return songs;
    }

    public void getTop5Songs (LocalDate date){
        MySearchBinaryTree<Integer, String> top5SongTree = new MySearchBinaryTreeImpl<>();
        MyHash<String, Song> songs = getSongData(date);
        for (int i = 0; i < songs.getSize(); i++) {
            Song song = songs.getIndex(i);
            if (song != null) {
                top5SongTree.add(song.getCounter(), song.getName());
            }
        }
        MyList<String> orderedList = top5SongTree.rightRootLeftTraversal();
        for (int i = 0; i < 5; i++) {
            String songname = orderedList.get(i);
            if (songname != null) {
                System.out.println("Top " + (i + 1) + " song of " + date.toString() + " : " + songname);
            } else {
                System.out.println("Top " + i + " not found.");
            }
        }
    }

    /* 3er reporte
        Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado. Cada
        aparición (como cada canción) distinta debe contarse, y se debe separar las
        canciones que tengan más de un artista contabilizando una aparición para cada uno.
        Si un artista tiene 10 canciones en el top 50, deben contabilizarse 10 ocurrencias.
     */

    public MyHash<String, Artist> insertSongsToArtists (DateRange dates){
        String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
        String path2 = "C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path1))) {
            return insertArtists(br, dates);
        } catch (FileNotFoundException e) {
            try (BufferedReader br = new BufferedReader(new FileReader(path2))) {
                return insertArtists(br, dates);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private MyHash<String, Artist> insertArtists (BufferedReader br, DateRange dates) throws IOException {
        MyHash<String, Artist> artists = new MyHashImpl<>();
        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            line = line.replace("\"\"", "\"").replaceAll("^\"|\"$", "");
            String[] data = line.split(",\"");
            if (data.length > 25) {
                data[7] = data[8];
                data[2] = data[3];
            }
            data[7] = data[7].replace("\"", "");
            LocalDate songDate = LocalDate.parse(data[7]);

            if (dates.contains(songDate)) {
                String[] artistNames = data[2].replaceAll("\"$", "").split(", ");
                for (String name : artistNames) {
                    //String artistName = name.replaceAll("\"$", "");
                    Artist artist = artists.getValue(name);
                    if (artist == null) {
                        Artist newArtist = new Artist(name);
                        artists.put(name, newArtist);
                        newArtist.increaseCounter();
                    } else {
                        artist.increaseCounter();
                    }
                }
            }
        }
        return artists;
    }


    public void getTop7Artists (DateRange dates){
        MySearchBinaryTree<Integer, String> top7ArtistTree = new MySearchBinaryTreeImpl<>();
        MyHash<String, Artist> artists = insertSongsToArtists(dates);
        for (int i = 0; i < artists.getSize(); i++) {
            Artist artist = artists.getIndex(i);
            if (artist != null) {
                top7ArtistTree.add(artist.getCounter(), artist.getName());
            }
        }
        MyList<String> orderedList = top7ArtistTree.rightRootLeftTraversal();
        for (int i = 0; i < 7; i++) {
            String artist = orderedList.get(i);
            if (artist != null) {
                System.out.println("Top " + (i + 1) + ": " + artist);
            } else {
                System.out.println("Top " + i + " not found.");
            }
        }
    }

    /* 4to reporte
        Cantidad de veces que aparece un artista específico en un top 50 en una fecha
        dada.
     */

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
        Artist artist = new Artist(nameArtist);
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
                String[] artistNames = data[2].replaceAll("\"$", "").split(", ");
                for (String name : artistNames) {
                    String artistName = name.replaceAll("\"$", "");
                    if (artistName.equals(nameArtist)) {
                        artist.increaseCounter();
                    }
                }
            }
        }
        System.out.println(artist.getName() + " had " + artist.getCounter() + " appearances on top50's on " + date.toString());
    }


    /* 5to reporte
        Cantidad de canciones con un tempo en un rango específico para un rango
        específico de fechas.
     */

    public Song findOrCountSong (String songName){
        MyHash<String, Song> songs = new MyHashImpl<>();
        Song song = songs.getValue(songName);
        if (song == null) {
            Song newSong = new Song(songName);
            songs.put(songName, newSong);
            return null;
        } else {
            return song;
        }
    }

    public void countSongsByTempo (DateRange dates,float minTempo, float maxTempo) {
        String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
        String path2 = "C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv";

        int count = 0;

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
                if (data.length > 25) {
                    data[7] = data[8];
                    data[23] = data[24];
                }
                LocalDate songDate = LocalDate.parse(data[7]);
                if (dates.contains(songDate)) {
                    float tempo = Float.parseFloat(data[23]); // data[23] es el tempo
                    if (tempo >= minTempo && tempo <= maxTempo) {
                        if(findOrCountSong(data[1]) == null) {
                            count++;
                        }
                    }
                }
            }

            System.out.println("Cantidad de canciones con tempo entre " + minTempo + " y " + maxTempo + ": " + count);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



