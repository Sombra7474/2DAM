package semana04.ejemplos.xml;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Piloto {
    @JacksonXmlProperty(localName = "name")
    private String nombre;

    @JacksonXmlProperty(localName = "number")
    private int numero;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")  // Especifica el formato de la fecha
    @JacksonXmlProperty(localName = "birthDate")
    private LocalDate fechaNacimiento;

    @JacksonXmlProperty(localName = "nationality")
    private String nacionalidad;
}
