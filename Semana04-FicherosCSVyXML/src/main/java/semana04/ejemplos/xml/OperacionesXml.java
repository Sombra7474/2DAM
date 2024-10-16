package semana04.ejemplos.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OperacionesXml {

    private static final Path XML_READ_FILE_PATH = Path.of(".", "src", "main", "resources", "f1teams.xml");
    private static final Path XML_WRITE_FILE_PATH = Path.of(".", "src", "main", "resources", "f1teamsOutput.xml");

    // Leer el archivo XML y convertirlo en una lista de objetos Equipo
    public static List<Equipo> leerEquiposDeXML() {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule()); // Soporte para LocalDate

        try (InputStream inputStream = Files.newInputStream(XML_READ_FILE_PATH)) {
            ListaEquipos listaEquipos = xmlMapper.readValue(inputStream, ListaEquipos.class);
            return listaEquipos.getEquipos();
        } catch (IOException e) {
            System.err.println("Error al leer el fichero");
            return List.of();
        }
    }

    // Escribir la lista de objetos Equipo en un archivo XML
    public static void escribirEquiposEnXML(List<Equipo> equipos) {
        ListaEquipos listaEquipos = new ListaEquipos(equipos);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule()); // Soporte para LocalDate
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT); // Formateo con saltos de línea y tabulaciones

        try (OutputStream outputStream = Files.newOutputStream(XML_WRITE_FILE_PATH)) {
            xmlMapper.writeValue(outputStream, listaEquipos);
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero");
        }
    }

    public static void main(String[] args) {
        // Leer equipos del archivo XML
        List<Equipo> equipos = leerEquiposDeXML();
        System.out.println("Equipos leídos del archivo XML:");
        equipos.forEach(System.out::println);

        // Escribir equipos en el archivo XML
        escribirEquiposEnXML(equipos);
    }
}
