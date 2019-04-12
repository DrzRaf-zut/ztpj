package pl.rafal.ztpj;

import pl.rafal.ztpj.dao.PracownikDao;
import pl.rafal.ztpj.model.Pracownik;
import pl.rafal.ztpj.ui.KopiaZapasowa;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandlerThread implements Runnable {

    private Socket socket;
    private InetAddress inet;
    private int port;


    public ClientHandlerThread(Socket s, InetAddress i, int p) {
        inet = i;
        port = p;
        socket = s;
    }

    @Override
    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            String token = inputStream.readUTF();


            if (!tokenExpired(token))
            {
                PracownikDao pracownikDao = new PracownikDao();
                List<Pracownik> lista_odczyt = pracownikDao.readFile(KopiaZapasowa.PATH + "Pracownicy.bin");

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(lista_odczyt);
                oos.flush();
                socket.close();
            }
            else
            {
                List<Pracownik> lista_expired = new ArrayList<>();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(lista_expired);
                oos.flush();
                socket.close();
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean tokenExpired(String token) {
        long expiryTime = Server.tokens.get(token);
        long difference = System.currentTimeMillis() - expiryTime;
        return difference > 120000;
    }
}
