package semana04.ejemplos.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Equipo {
    @JacksonXmlProperty(localName = "name")
    private String nombre;

    @JacksonXmlProperty(localName = "nationality")
    private String nacionalidad;

    @JacksonXmlElementWrapper(localName = "drivers") // Envolver la lista de pilotos en una etiqueta "Drivers"
    @JacksonXmlProperty(localName = "driver") // Etiqueta personalizada para cada piloto
    private List<Piloto> pilotos;
}
