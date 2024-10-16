package semana04.ejercicio.xml;


import java.nio.file.Path;
import java.util.List;

public class OperacionesXml {
    private static final Path CSV_READ_FILE_PATH = Path.of(".", "src", "main", "resources", "funkos.xml");
    private static final Path CSV_WRITE_FILE_PATH = Path.of(".", "src", "main", "resources", "funkosOutput.xml");

    public static List<Funko> leerFunkosDeXML() {
        return List.of();
    }

    public static void escribirFunkosEnXML(List<Funko> funkos) {

    }

    public static void main(String[] args) {
        List<Funko> funkos = leerFunkosDeXML();
        System.out.println("Funkos leídos del archivo CSV:");
        funkos.forEach(System.out::println);

        escribirFunkosEnXML(funkos);
    }
}
