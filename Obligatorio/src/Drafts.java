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