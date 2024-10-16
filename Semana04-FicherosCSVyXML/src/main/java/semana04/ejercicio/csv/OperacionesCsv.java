package semana04.ejercicio.csv;

import java.nio.file.Path;
import java.util.List;

public class OperacionesCsv {
    private static final Path CSV_READ_FILE_PATH = Path.of(".", "src", "main", "resources", "funkos.csv");
    private static final Path CSV_WRITE_FILE_PATH = Path.of(".", "src", "main", "resources", "funkosOutput.csv");

    public static List<Funko> leerFunkosDeCSV() {
        return List.of();
    }

    public static void escribirFunkosEnCSV(List<Funko> funkos) {

    }

    public static void main(String[] args) {
        List<Funko> funkos = leerFunkosDeCSV();
        System.out.println("Funkos leídos del archivo CSV:");
        funkos.forEach(System.out::println);

        escribirFunkosEnCSV(funkos);
    }
}
