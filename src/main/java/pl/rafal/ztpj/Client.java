package pl.rafal.ztpj;

import pl.rafal.ztpj.model.Pracownik;
import pl.rafal.ztpj.rmi.Validator;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class Client {

    private static Validator look_up;
    private String token;


    public boolean authenticate(String login, String pass) throws RemoteException, NotBoundException, MalformedURLException {


        look_up = (Validator) Naming.lookup("localhost");

        token = look_up.validate(login,pass);

        return validateToken(token);

    }

    private boolean validateToken(String token) {
        if (token.isEmpty()) {
            System.out.println("Błędne dane logowania");
            return false;
        }
        return true;
    }


    public List<Pracownik> runClient(String host, int port) throws IOException, ClassNotFoundException{

        Socket socket1 = new Socket(host,Server.PORT, InetAddress.getByName(host),port);

        DataOutputStream dataOutputStream = new DataOutputStream(socket1.getOutputStream());
        dataOutputStream.writeUTF(token);
        dataOutputStream.flush();

        ObjectInputStream ois = new ObjectInputStream(socket1.getInputStream());
        List<Pracownik> lista_pobrana = (List<Pracownik>)ois.readObject();

        socket1.close();

        return lista_pobrana;
    }

}
