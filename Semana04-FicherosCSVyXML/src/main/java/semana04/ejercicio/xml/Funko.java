package semana04.ejercicio.xml;

import java.io.File; // Usamos esto para trabajar con archivos
import java.io.IOException; // Esto nos ayuda a manejar errores cuando trabajamos con archivos
import java.time.LocalDate; // Esto representa fechas (día, mes, año)
import java.util.List; // Lista especial donde podemos añadir muchos objetos
import java.util.UUID; // Esto es un identificador único (ID) que nadie más tiene

import com.fasterxml.jackson.annotation.JsonFormat; // Esto ayuda a dar formato a las fechas
import com.fasterxml.jackson.dataformat.xml.XmlMapper; // Usamos esto para leer y escribir archivos XML
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty; // Esto ayuda a dar nombres a los elementos en XML
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule; // Esto nos ayuda a trabajar con fechas modernas

public class Funko {
    @JacksonXmlProperty(localName = "codigo")
    private UUID codigo; // El código especial del Funko

    @JacksonXmlProperty(localName = "nombre")
    private String nombre; // El nombre del Funko

    @JacksonXmlProperty(localName = "modelo")
    private String modelo; // El modelo del Funko

    @JacksonXmlProperty(localName = "precio")
    private double precio; // Cuánto cuesta el Funko

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JacksonXmlProperty(localName = "fechaLanzamiento")
    private LocalDate fechaLanzamiento; // Cuándo salió el Funko

    public Funko() {
        // Constructor sin argumentos (para crear un Funko vacío)
    }

    // Este es el constructor, que nos ayuda a crear un nuevo Funko con datos
    public Funko(UUID codigo, String nombre, String modelo, double precio, LocalDate fechaLanzamiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.modelo = modelo;
        this.precio = precio;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    // Getters y setters (para obtener y cambiar los datos del Funko)
    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    @Override
    public String toString() {
        // Esto convierte al Funko en una cadena de texto para imprimir
        return nombre + " (" + modelo + ") - €" + precio + " - Lanzado el " + fechaLanzamiento;
    }

    public static void main(String[] args) {
        // Rutas a los archivos XML
        String inputFilePath = "src\\main\\resources\\funkos.xml";
        String outputFilePath2 = "src\\main\\resources\\funkos2.xml";

        XmlMapper xmlMapper = new XmlMapper(); // Crear un objeto para trabajar con XML
        xmlMapper.registerModule(new JavaTimeModule()); // Registrar el módulo para trabajar con fechas

        try {
            // Leer los Funkos del archivo XML
            List<Funko> readFunkos = xmlMapper.readValue(new File(inputFilePath),
                    xmlMapper.getTypeFactory().constructCollectionType(List.class, Funko.class));

            readFunkos.forEach(System.out::println); // Imprimir cada Funko

            // Escribir los Funkos en un nuevo archivo XML
            xmlMapper.writeValue(new File(outputFilePath2), readFunkos);
        } catch (IOException e) {
            e.printStackTrace(); // Imprimir el error si hay problemas
        }
    }
}





