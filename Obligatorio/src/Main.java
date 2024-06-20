
import uy.edu.um.prog2.adt.binarytree.MySearchBinaryTree;
import entities.DateRange;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Spotify spotify = new Spotify();
        Scanner scanner = new Scanner(System.in);

        //          TESTS
        //spotify.OrderTop10("ZA", "2024-05-13" );
        //System.out.println("done");
        //String filePath = "Dataset obligatorio.csv";
        //spotify.loadData(filePath);
        //spotify.OrderTop10("ZA", "2024-05-13" );
        //DateRange dateRange = new DateRange("2024-05-01", "2024-05-31");
        //spotify.getTop7Artists(dateRange);
        //System.out.println("done");

        //Test reporte 5 - todavia no lo probe
        //DateRange dateRange = new DateRange("2024-05-01", "2024-05-31");
        //float minTempo = 100.0f;
        //float maxTempo = 120.0f;
        //spotify.countSongsByTempo(dateRange, minTempo, maxTempo);
        //System.out.println("done");

        while(true){
            System.out.println("Seleccione el reporte que desea realizar: ");
            System.out.println("1. Top 10 canciones en un país en un día dado.");
            System.out.println("2. Top 5 canciones que aparecen en más top 50 en un día dado.");
            System.out.println("3. Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado.");
            System.out.println("4. Cantidad de veces que aparece un artísta específico en un top 50 en una fecha dada.");
            System.out.println("5. Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas.");
            System.out.println("6. Salir.");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Ingrese el país:");
                    String pais = scanner.nextLine();
                    System.out.println("Ingrese la fecha (YYYY-MM-DD):");
                    String date = scanner.nextLine();
                    MySearchBinaryTree<String, ArrayList<String>> tree = spotify.Top10tree(pais, date);
                    spotify.printTop10Songs(tree);
                    break;
                case 2:
                    System.out.println("Ingrese la fecha (YYYY-MM-DD):");
                    String date2 = scanner.nextLine();
                    spotify.printTop5Songs(date2);
                    break;
                case 3:
                    System.out.println("Ingrese la fecha de inicio (YYYY-MM-DD):");
                    String startDate = scanner.nextLine();
                    System.out.println("Ingrese la fecha de fin (YYYY-MM-DD):");
                    String endDate = scanner.nextLine();
                    DateRange range = new DateRange(startDate, endDate);
                    spotify.getTop7Artists(range);
                    break;
                case 4:
                    System.out.println("Ingrese la fecha (YYYY-MM-DD):");
                    String date3 = scanner.nextLine();
                    System.out.println("Ingrese el nombre del artista:");
                    String artistName = scanner.nextLine();
                    spotify.getArtistCount(date3, artistName);
                    break;
                case 5:
                    System.out.println("Ingrese la fecha de inicio (YYYY-MM-DD):");
                    String startDate2 = scanner.nextLine();
                    System.out.println("Ingrese la fecha de fin (YYYY-MM-DD):");
                    String endDate2 = scanner.nextLine();
                    System.out.println("Ingrese el tempo mínimo:");
                    float minTempo = scanner.nextFloat();
                    System.out.println("Ingrese el tempo máximo:");
                    float maxTempo = scanner.nextFloat();
                    DateRange tempoRange = new DateRange(startDate2, endDate2);
                    spotify.countSongsByTempo(tempoRange, minTempo, maxTempo);
                    break;
                case 6:
                    System.out.println("Ha salido del menu");
                    return;
                default:
                    System.out.println("La opción ingresada es invalida. Por favor, intente nuevamente.");
            }
        }
    }
}