import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Spotify spotify = new Spotify();
//        spotify.CreateHashTop10();
        Scanner scanner = new Scanner(System.in);

        //String filePath = "Dataset obligatorio.csv";
        //spotify.loadData(filePath);
        spotify.OrderTop10("ZA", "2024-05-13" );
        System.out.println("done");
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
                    System.out.println("1");
                    //String country = scanner.nextLine();
                    //System.out.println("Ingrese la fecha (YYYY-MM-DD):");
                    //String date = scanner.nextLine();
                    // ...
                    break;
                case 2:
                    System.out.println("2");
                    break;
                case 3:
                    System.out.println("3");
                    break;
                case 4:
                    System.out.println("4");
                    break;
                case 5:
                    System.out.println("5");
                    break;
                case 6:
                    System.out.println("6");
                    scanner.close();
                    return;
                default:
                    System.out.println("La opción ingresada es invalida. Por favor, intente nuevamente.");
            }
        }
    }

}