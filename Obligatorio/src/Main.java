
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
                    
                    long memoryBefore1 = MemoryMeasurement.getUsedMemory();
                    long report1Time = TimeMeasurement.measureExecutionTime(() -> {
                        MySearchBinaryTree<Integer, ArrayList<String>> tree = null;
                        try {
                            tree = spotify.Top10tree(pais, date);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        spotify.printTop10Songs(tree);
                    });
                    long memoryAfter1 = MemoryMeasurement.getUsedMemory();
                    long memoryUsedFor1 = memoryAfter1 - memoryBefore1;

                    System.out.println("Memoria utilizada para Reporte 1: " + memoryUsedFor1 + " bytes");
                    System.out.println("Tiempo de ejecución para Reporte 1: " + report1Time + " ms");
                    break;

                case 2:
                    System.out.println("Ingrese la fecha (YYYY-MM-DD):");
                    LocalDate date2 = LocalDate.parse(scanner.nextLine());
                    long memoryBefore2 = MemoryMeasurement.getUsedMemory();
                    long report2Time = TimeMeasurement.measureExecutionTime(() -> {
                        spotify.getTop5Songs(date2);
                    });
                    long memoryAfter2 = MemoryMeasurement.getUsedMemory();
                    long memoryUsedFor2 = memoryAfter2 - memoryBefore2;

                    System.out.println("Memoria utilizada para Reporte 2: " + memoryUsedFor2 + " bytes");
                    System.out.println("Tiempo de ejecución para Reporte 2: " + report2Time + " ms");
                    break;

                case 3:
                    System.out.println("Ingrese la fecha de inicio (YYYY-MM-DD):");
                    String startDate = scanner.nextLine();
                    System.out.println("Ingrese la fecha de fin (YYYY-MM-DD):");
                    String endDate = scanner.nextLine();
                    DateRange range = new DateRange(startDate, endDate);

                    long memoryBefore3 = MemoryMeasurement.getUsedMemory();
                    long report3Time = TimeMeasurement.measureExecutionTime(() -> {
                        spotify.getTop7Artists(range);
                    });
                    long memoryAfter3 = MemoryMeasurement.getUsedMemory();
                    long memoryUsedFor3 = memoryAfter3 - memoryBefore3;

                    System.out.println("Memoria utilizada para Reporte 3: " + memoryUsedFor3 + " bytes");
                    System.out.println("Tiempo de ejecución para Reporte 3: " + report3Time + " ms");
                    break;

                case 4:
                    System.out.println("Ingrese la fecha (YYYY-MM-DD):");
                    LocalDate date3 = LocalDate.parse(scanner.nextLine());
                    System.out.println("Ingrese el nombre del artista:");
                    String artistName = scanner.nextLine();

                    long memoryBefore4 = MemoryMeasurement.getUsedMemory();
                    long report4Time = TimeMeasurement.measureExecutionTime(() -> {
                        spotify.getArtistData(date3, artistName);
                    });
                    long memoryAfter4 = MemoryMeasurement.getUsedMemory();
                    long memoryUsedFor4 = memoryAfter4 - memoryBefore4;

                    System.out.println("Memoria utilizada para Reporte 4: " + memoryUsedFor4 + " bytes");
                    System.out.println("Tiempo de ejecución para Reporte 4: " + report4Time + " ms");
                    break;


                case 5:
                    System.out.println("Ingrese la fecha de inicio (YYYY-MM-DD):");
                    String startDate2 = scanner.nextLine();
                    System.out.println("Ingrese la fecha de fin (YYYY-MM-DD):");
                    String endDate2 = scanner.nextLine();
                    System.out.println("Ingrese el tempo mínimo:");
                    Float minTempo = scanner.nextFloat();
                    System.out.println("Ingrese el tempo máximo:");
                    Float maxTempo = scanner.nextFloat();
                    DateRange tempoRange = new DateRange(startDate2, endDate2);

                    long memoryBefore5 = MemoryMeasurement.getUsedMemory();
                    long report5Time = TimeMeasurement.measureExecutionTime(() -> {
                        spotify.countSongsByTempo(tempoRange, minTempo, maxTempo);
                    });
                    long memoryAfter5 = MemoryMeasurement.getUsedMemory();
                    long memoryUsedFor5 = memoryAfter5 - memoryBefore5;

                    System.out.println("Memoria utilizada para Reporte 5: " + memoryUsedFor5 + " bytes");
                    System.out.println("Tiempo de ejecución para Reporte 5: " + report5Time + " ms");
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