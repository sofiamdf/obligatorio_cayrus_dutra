import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;



    // public static void main(String[] args) throws FileNotFoundException {

       // String path1 = "/Users/aguscayrus/universal_top_spotify_songs-1.csv";
       // String path2 = "\"C:\\Users\\smdf2\\OneDrive\\Escritorio\\Facultad\\3er Semestre\\Programación 2\\Dataset obligatorio.csv\"";


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
//            while((line = br.readLine()) != null) {
//                String[] data = line.split(",");
//                for (int i = 0; i < data.length; i++) {
//                    // Remove double quotes from each element
//                    data[i] = data[i].replace("\"", "");
//                }
//                System.out.println(data[0] + " " + data[1] + " " + data[2] + " " + data[3] + " " + data[4] + " " + data[5] + " " + data[6] + " " + data[7] + " " + data[8] + " " + data[9] + " " + data[10] + " " + data[11] + " " + data[12] + " " + data[13] + " " + data[14] + " " + data[15] + " " + data[16] + " " + data[17] + " " + data[18] + " " + data[19] + " " + data[20] + " " + data[21] + " " + data[22] + " " + data[23] + " " + data[24]);
//                break;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        /*HashMap<String, String[]> songDataMap = new HashMap<>();
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
                songDataMap.put(data[0], data);
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

        // Now, you have all the data stored in the HashMap
        // You can access it as needed
        // For example, to print the data of a specific song:
//        String[] songData = songDataMap.get("song_identifier");
//        if (songData != null) {
//            for (String attribute : songData) {
//                System.out.print(attribute + " ");
//            }
//        } else {
//            System.out.println("Song not found");
//        }
    }

         */

