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
    MySearchBinaryTree<Integer, String> top5SongTree = new MySearchBinaryTreeImpl<>();
    MyHash<String, Song> songs = new MyHashImpl<>();

    // 1er reporte

    public MySearchBinaryTree<Integer, ArrayList<String>> Top10tree(String pais, String date) throws FileNotFoundException {
        String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
        String path2 = "\"C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv\"";

        MySearchBinaryTree<Integer, ArrayList<String>> top10songTree = new MySearchBinaryTreeImpl<>();

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
                if (currentCountry.equals(pais) && currentDate.equals(date)) {
                    ArrayList<String> list = new ArrayList();
                    list.add(data[1]);
                    list.add(data[2]);
                    list.add(data[3]);
                    //TODO: filter songs where Rank > 10
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
        for (int i = 0; i < inOrderList.size(); i++) {
            ArrayList<String> song = inOrderList.get(i);
            System.out.println("Puesto: " + song.get(2) + ", Canción: " + song.get(0) + ", Artista: " + song.get(1));
        }
    }

    public void OrderTop10(String country, String date) throws FileNotFoundException {
//             String[] key = {country, date.toString()};
//             MyList<String[]> Songs = Top10songHash.get(key);
//             top10songTree.

        MySearchBinaryTree<Integer, ArrayList<String>> top10 = Top10tree(country, date);
        MyList<ArrayList<String>> inOrderList = top10.inOrderWithValues();
        for (int i = 1; i <= 10; i++) {
            ArrayList<String> song = inOrderList.get(i);
            System.out.println("Puesto: " + song.get(2) + ", Canción: " + song.get(0) + ", Artista: " + song.get(1));
        }
    }

    // 2do reporte

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

    // 3er reporte

    public void insertSongsToArtists(DateRange dates) {
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

            private void insertArtists(BufferedReader br, DateRange dates) throws IOException {
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
                        String[] artistNames = data[2].replaceAll("\"$","").split(", ");
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

    public Artist findOrCreateArtist(String artistName) {
        Artist newArtist = new Artist(artistName);
        if (artists.contains(newArtist)) {
            for (int i = 0; i < artists.size(); i++) {
                if (artists.get(i).equals(newArtist)) {
                    return artists.get(i);
                }
            }
        }
        // Logic to find or create Artist objects goes here
        // For simplicity, let's assume we're just creating new Artist objects each time
        return new Artist(artistName);
    }


    public void getTop7Artists(DateRange dates) {
        insertSongsToArtists(dates);
        for (int i = 0; i < artists.size(); i++) {
            top7ArtistTree.add(artists.get(i).getCounter(), artists.get(i).getName());
        }
        MyList orderdList = top7ArtistTree.rightRootLeftTraversal();
        for (int i = 1; i <= 7; i++) {
            String artist = orderdList.get(i).toString(); // Use String keys
            if (artist != null) {
                System.out.println("Top " + i + ": " + artist);
            } else {
                System.out.println("Top " + i + " not found.");
            }
        }
    }

    // 4to reporte

    public void getArtistCount(String date, String nameArtist){
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
                    Artist artist = new Artist(nameArtist);
                    while ((line = br.readLine()) != null) {
                        line = line.replace("\"\"", "\"").replaceAll("^\"|\"$", "");
                        String[] data = line.split(",\"");
                        for (int i = 0; i < data.length; i++) {
                            data[i] = data[i].replace("\"", "");
                        }
                        if (data[7].equals(date)) {
                            String[] artistNames = data[2].split(", ");  // Assuming data[2] is the artist field
                            for (String artistName : artistNames) {
                                if (artistName.equals(nameArtist)) {
                                    // Create a new Artist object or retrieve existing one if available
                                    artist.increaseCounter();
                                }
                                // Output for verification (print or further processing)
                            }
                        }
                    }
                    System.out.println(artist.getCounter());
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

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

    // 5to reporte

    public void countSongsByTempo(DateRange dates, float minTempo, float maxTempo) {
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
                LocalDate songDate = LocalDate.parse(data[7]);
                if (dates.contains(songDate)) {
                    float tempo = Float.parseFloat(data[23]); // data[23] es el tempo
                    if (tempo >= minTempo && tempo <= maxTempo) {
                        count++;
                    }
                }
            }

            System.out.println("Cantidad de canciones con tempo entre " + minTempo + " y " + maxTempo + ": " + count);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



