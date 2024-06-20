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
//    public void OrderTop10(String country, String date) throws FileNotFoundException {
////             String[] key = {country, date.toString()};
////             MyList<String[]> Songs = Top10songHash.get(key);
////             top10songTree.
//
//        MySearchBinaryTree top10 = Top10tree(country, date);
//        for (int i = 1; i <= 10; i++) {
//            ArrayList<String> songs = (ArrayList<String>) top10.find(String.valueOf(i)); // Use String keys
//            if (songs != null) {
//                System.out.println("Top " + i + ": " + songs);
//            } else {
//                System.out.println("Top " + i + " not found.");
//            }
//        }
//    }
//
//
//    public MySearchBinaryTree<String,ArrayList<String>> Top5tree(String pais, String date) throws FileNotFoundException {
//        String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
//        String path2 = "\"C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv\"";
//
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
//                    // Remove double quotes from each element
//                    data[i] = data[i].replace("\"", "");
//                }
//                if (data[7].equals(date)) {
//                    if (top5List.contains(data[1])) {
//                        top5List.get
//                    }
//                    top5List.put(data[1], 1);
//                }
//            }
//            return top10songTree;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

/*


public class ObligatorioImpl implements Obligatorio {

    @Override
    public List<Map<String, String>> top10(String country, String date) {
        List<Map<String, String>> top10Songs = new ArrayList<>();
        String path = "/path/to/your/Dataset obligatorio.csv"; // Cambia esta ruta por la correcta

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Limpiar comillas dobles duplicadas
                line = line.replace("\"\"", "\"").replaceAll("^\"|\"$", "");
                String[] data = line.split(",\"");

                // Filtrar por país y fecha
                if (data.length > 7 && data[6].equals(country) && data[7].equals(date)) {
                    Map<String, String> songData = new HashMap<>();
                    songData.put("name", data[1]);
                    songData.put("artist", data[2]);
                    songData.put("rank", data[3]);
                    top10Songs.add(songData);
                }
            }

            // Ordenar por rango en orden descendente
            top10Songs.sort(Comparator.comparingInt(song -> -Integer.parseInt(song.get("rank"))));

            // Obtener solo los top 10
            if (top10Songs.size() > 10) {
                top10Songs = top10Songs.subList(0, 10);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return top10Songs;
    }
}
 */

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