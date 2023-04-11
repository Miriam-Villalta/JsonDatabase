import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import ServerCall.ServerCall;

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

        ServerCall server = new ServerCall();
        server.serverCall();

    }
}