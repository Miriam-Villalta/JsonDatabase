import org.h2.tools.Server;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static <BufferReader> void main(String[] args) throws Exception {
        String pathFiles = "C:/Users/Miriam/IdeaProjects/";
        String fileIncome = "ingresos.csv";
        String fileJson = "ingresos.json";

        Path path = FileSystems.getDefault().getPath("C:/Users/Miriam/IdeaProjects/ingresos.json");


        List<Ingreso> ingresos = CsvToJson.fromCsv(pathFiles, fileIncome);

        CsvToJson.toJson(ingresos, pathFiles + fileJson);
        List<Ingreso> json = CsvToJson.readJson(path);
        CsvToJson.insert(json);

        /*ServerCall server = new ServerCall();
        server.serverCall();*/
        Server server = Server.createWebServer().start();
        Server.openBrowser(server.getURL());

        System.out.println("Pulsa enter para finalizar");
        (new Scanner(System.in)).nextLine();
        server.stop();

        /* url = "jdbc:h2:~/test";
        String user = "sa";
        String password = "";

        Connection connection = CsvToJson.getConnection();
        Statement command = connection.createStatement();
        String sql1 = "";

        command.execute(""); //para insertar
        command.close();
        connection.close();*/
    }
}