package semana04.ejemplos.csv;

import com.opencsv.*;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class OperacionesCsv {
    private static final Path CSV_READ_FILE_PATH = Path.of(".", "src", "main", "resources", "f1drivers.csv");
    private static final Path CSV_WRITE_FILE_PATH = Path.of(".", "src", "main", "resources", "f1driversOutput.csv");

    // Leer Pilotos usando CsvToBean
    public static List<Piloto> leerPilotosDeCSV() {
        try (Reader reader = Files.newBufferedReader(CSV_READ_FILE_PATH)) {
            // CsvToBeanBuilder es ideal para convertir líneas de CSV a objetos
            CsvToBean<Piloto> csvToBean = new CsvToBeanBuilder<Piloto>(reader)
                    //.withSeparator(',') // Separador de columnas, por defecto es la coma
                    .withType(Piloto.class)
                    .withIgnoreLeadingWhiteSpace(true) // Ignora espacios en blanco al principio de la línea
                    .build();

            // Devolvemos una lista de objetos Piloto
            return csvToBean.parse();
        } catch (Exception e) {
            System.err.println("Error al leer el fichero");
            return List.of();
        }
    }

    // Escribir una lista de objetos Piloto en el archivo CSV usando OpenCSV
    public static void escribirPilotosEnCSV(List<Piloto> pilotos) {
        try (Writer writer = Files.newBufferedWriter(CSV_WRITE_FILE_PATH)) {
            // Configura el beanToCsv
            StatefulBeanToCsv<Piloto> beanToCsv = new StatefulBeanToCsvBuilder<Piloto>(writer)
                    // .withMappingStrategy(mappingStrategy) // Usa la estrategia personalizada
                    //.withSeparator(',')
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER) // Configura el caracter de comillas
                    .build();
            beanToCsv.write(pilotos);
        } catch (Exception e) {
            System.err.println("Error al escribir el fichero: " + e.getMessage());
        }
    }

    // Escribir una lista de objetos Piloto en el archivo CSV usando OpenCSV
    public static void escribirPilotosEnCSVManual(List<Piloto> pilotos) {
        try (Writer writer = Files.newBufferedWriter(CSV_WRITE_FILE_PATH)) {

            String lineaCabecera = Arrays.stream(Piloto.class.getDeclaredFields())
                    .map(field -> field.getAnnotation(CsvBindByName.class))
                    .filter(Objects::nonNull)
                    .map(CsvBindByName::column) // Mantiene el texto exacto de la anotación
                    .collect(Collectors.joining(","));

            // Escribe la cabecera
            writer.write(lineaCabecera + "\n");

            // Imprime los pilotos en el archivo CSV
            for (Piloto piloto : pilotos) {
                String linea = String.join(",",
                        piloto.getNombre(),
                        String.valueOf(piloto.getNumero()),
                        piloto.getFechaNacimiento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        piloto.getNacionalidad(),
                        piloto.getEquipo());
                writer.write(linea + "\n");
            }

        } catch (Exception  e) {
            System.err.println("Error al escribir el fichero");
        }
    }

    public static void main(String[] args) {
        // Leer Pilotos del archivo CSV
        List<Piloto> pilotos = leerPilotosDeCSV();
        System.out.println("Pilotos leídos del archivo CSV:");
        pilotos.forEach(System.out::println);

        // Escribir Pilotos en el archivo CSV
        escribirPilotosEnCSVManual(pilotos);
    }
}
