package semana04.ejemplos.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Piloto {
    //@CsvBindByPosition(position = 0) // Permite mapear columnas por posición
    @CsvBindByName(column = "name") // Permite mapear columnas por nombre
    private String nombre;

    //@CsvBindByPosition(position = 1)
    @CsvBindByName(column = "number")
    private int numero;

    //@CsvBindByPosition(position = 2)
    @CsvBindByName(column = "birthDate")
    @CsvDate("yyyy-MM-dd") // Formato de fecha
    private LocalDate fechaNacimiento;

    //@CsvBindByPosition(position = 3)
    @CsvBindByName(column = "nationality")
    private String nacionalidad;

    //@CsvBindByPosition(position = 4)
    @CsvBindByName(column = "team")
    private String equipo;
}
