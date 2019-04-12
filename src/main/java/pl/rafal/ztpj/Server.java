package pl.rafal.ztpj;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {

    public static final int PORT = 8191;
    public static HashMap<String, Long> tokens;

    public void runServer() throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        while (true) {
            Socket incoming = s.accept();
            System.out.println("Łączenie...");
            Runnable r = new ClientHandlerThread(incoming, incoming.getInetAddress(),incoming.getPort());
            Thread t = new Thread(r);
            t.start();
        }
    }
}
