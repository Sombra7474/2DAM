package semana04.ejercicio.csv;

import java.io.BufferedReader; // Usamos esto para leer archivos de texto
import java.io.FileReader; // Usamos esto para abrir archivos de texto para leer
import java.io.FileWriter; // Usamos esto para escribir en archivos de texto
import java.io.IOException; // Esto nos ayuda a manejar errores cuando trabajamos con archivos
import java.time.LocalDate; // Esto representa fechas (día, mes, año)
import java.time.format.DateTimeFormatter; // Esto nos ayuda a convertir fechas a texto y viceversa
import java.util.ArrayList; // Lista especial donde podemos añadir muchos objetos
import java.util.List; // Esto también es una lista para guardar objetos
import java.util.UUID; // Esto es un identificador único (ID) que nadie más tiene

public class Funko {
    // Estas son las características (variables) de un Funko
    private UUID codigo; // El código especial del Funko
    private String nombre; // El nombre del Funko
    private String modelo; // El modelo del Funko
    private double precio; // Cuánto cuesta el Funko
    private LocalDate fechaLanzamiento; // Cuándo salió el Funko

    // Este es el constructor, que nos ayuda a crear un nuevo Funko
    public Funko(UUID codigo, String nombre, String modelo, double precio, LocalDate fechaLanzamiento) {
        this.codigo = codigo; // Asignar el código único
        this.nombre = nombre; // Asignar el nombre
        this.modelo = modelo; // Asignar el modelo
        this.precio = precio; // Asignar el precio
        this.fechaLanzamiento = fechaLanzamiento; // Asignar la fecha de lanzamiento
    }

    @Override
    public String toString() {
        // Esto convierte al Funko en una cadena de texto para imprimir
        return nombre + " (" + modelo + ") - €" + precio + " - Lanzado el " + fechaLanzamiento;
    }

    // Este método lee un archivo CSV y convierte las líneas en Funkos
    public static List<Funko> readCSVToFunkos(String filePath) {
        List<Funko> funkos = new ArrayList<>(); // Crear una lista para guardar Funkos
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato para las fechas
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { // Abrir el archivo para leer
            br.readLine(); // Leer y saltar la cabecera (los títulos)
            String line;
            while ((line = br.readLine()) != null) { // Leer cada línea hasta el final
                String[] values = line.split(","); // Dividir la línea por comas
                Funko funko = new Funko(UUID.fromString(values[0]), values[1], values[2], 
                                        Double.parseDouble(values[3]), LocalDate.parse(values[4], formatter));
                funkos.add(funko); // Añadir el Funko a la lista
            }
        } catch (IOException e) {
            e.printStackTrace(); // Imprimir el error si hay problemas
        }
        return funkos; // Devolver la lista de Funkos
    }

    // Este método escribe una lista de Funkos en un archivo CSV
    public static void writeFunkosToCSV(String filePath, List<Funko> funkos) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato para las fechas
        try (FileWriter writer = new FileWriter(filePath)) { // Abrir el archivo para escribir
            writer.append("CODIGO,NOMBRE,MODELO,PRECIO,FECHA_LANZAMIENTO\n"); // Escribir la cabecera
            for (Funko funko : funkos) { // Para cada Funko en la lista
                writer.append(funko.codigo.toString())
                      .append(',').append(funko.nombre)
                      .append(',').append(funko.modelo)
                      .append(',').append(String.valueOf(funko.precio))
                      .append(',').append(funko.fechaLanzamiento.format(formatter))
                      .append('\n'); // Escribir el Funko en el archivo
            }
        } catch (IOException e) {
            e.printStackTrace(); // Imprimir el error si hay problemas
        }
    }

    // Este es el método principal, donde comienza el programa
    public static void main(String[] args) {
        // Rutas a los archivos CSV
        String inputFilePath = "H:\\2DAM\\AccesoDatos-2024-2025-02-main\\AccesoDatos-2024-2025-02-main\\Semana04-FicherosCSVyXML\\src\\main\\resources\\funkos.csv";
        String outputFilePath = "H:\\2DAM\\AccesoDatos-2024-2025-02-main\\AccesoDatos-2024-2025-02-main\\Semana04-FicherosCSVyXML\\src\\main\\resources\\funko2.csv";

        // Leer los Funkos del archivo CSV
        List<Funko> funkos = readCSVToFunkos(inputFilePath);
        for (Funko funko : funkos) { // Para cada Funko en la lista
            System.out.println(funko); // Imprimir el Funko
        }

        // Escribir los Funkos en un nuevo archivo CSV
        writeFunkosToCSV(outputFilePath, funkos);
    }
}




