import org.h2.tools.Server;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String pathFiles = "C:/Users/Miriam/IdeaProjects/";
        String fileIncome = "ingresos.csv";
        String fileJson = "ingresos.json";


        List<Ingreso> ingresos = CsvToJson.fromCsv(pathFiles, fileIncome);

        CsvToJson.toJson(ingresos, pathFiles + fileJson);

        /*ServerCall server = new ServerCall();
        server.serverCall();*/
        Server server = Server.createWebServer().start();
        Server.openBrowser(server.getURL());

        System.out.println("Pulsa enter para finalizar");
        (new java.util.Scanner(System.in)).nextLine();
        server.stop();

        String url = "jdbc:h2:~/test";
        String user = "sa";
        String password = "";

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement command = connection.createStatement();
        String sql1 = "";

        command.execute(""); //para insertar
        command.close();
        connection.close();
    }
}