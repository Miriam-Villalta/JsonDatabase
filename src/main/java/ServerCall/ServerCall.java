package ServerCall;
import org.h2.tools.Server;

import java.sql.SQLException;

public class ServerCall {
    public void serverCall() throws Exception {
        Server server = Server.createWebServer().start();
        Server.openBrowser(server.getURL());

        System.out.println("Pulsa enter para finalizr");
        (new java.util.Scanner(System.in)).nextLine();
        server.stop();
    }

}
