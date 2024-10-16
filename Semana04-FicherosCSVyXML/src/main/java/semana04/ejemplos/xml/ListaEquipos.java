package semana04.ejemplos.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor // XML no lo necesita, pero nos permite inicializar la lista de equipos con los equipos leídos
@JacksonXmlRootElement(localName = "f1Teams")  // Raíz del documento XML
public class ListaEquipos {
    @JacksonXmlElementWrapper(useWrapping = false) // No envolvemos la lista en una etiqueta adicional
    @JacksonXmlProperty(localName = "team")  // Personaliza la etiqueta de cada equipo
    private List<Equipo> equipos;
}
