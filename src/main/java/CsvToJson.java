import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CsvToJson {

    static String url = "jdbc:h2:~/test";
    static String user = "sa";
    static String password = "";

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void insert(List<Ingreso> ingresos) throws SQLException {
        final String sql = "INSERT INTO Ingreso(id, anho, clasificacion, empresa, ingresos) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = getConnection()){
        Statement dropTable = con.createStatement();
        dropTable.execute("TRUNCATE TABLE Ingreso");
            for (Ingreso nuevoIngreso : ingresos) {
                //sql = "INSERT INTO Ingreso(" + nuevoIngreso + ") VALUES (id, anho, clasificacion, empresa, ingresos)";
                try(PreparedStatement comando = con.prepareStatement(sql)){
                    comando.setInt(1, nuevoIngreso.getId());
                    comando.setInt(2, nuevoIngreso.getAnho());
                    comando.setInt(3, nuevoIngreso.getClasificacion());
                    comando.setString(4, nuevoIngreso.getEmpresa());
                    comando.setDouble(5, nuevoIngreso.getIngresos());
                    comando.execute();

                }
            }
        }

        //por cada ingreso, añade un registro
    }

    public static List<Ingreso> fromCsv(String path, String filename) throws IOException {
        List<Ingreso> data = new ArrayList<>();

        Path pathCsv = Path.of(path, filename);
        CSVReader csvReader = new CSVReader(new FileReader(pathCsv.toString()));
        csvReader.skip(1);
        csvReader.forEach(linea -> data.add(Ingreso.from(linea)));

        for (int i = 0; i < data.size(); i++){
            Ingreso fila = data.get(i);
            fila.setId(i+1);
        }
        return data;
    }

    public static void toJson(List< Ingreso > ingresos, String path) throws IOException {
        //Write
        ObjectMapper obj = new ObjectMapper();
        obj.writeValue(new File(path), ingresos);
    }

    public static List<Ingreso> readJson(Path path) throws IOException {
        ObjectMapper obj = new ObjectMapper();
        return obj.readValue(path.toFile(), new TypeReference<List<Ingreso>>() {});
    }
}
