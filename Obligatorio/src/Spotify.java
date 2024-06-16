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

    MySearchBinaryTree<String, ArrayList<String>> top10songTree = new MySearchBinaryTreeImpl<String, ArrayList<String>>();

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
}



