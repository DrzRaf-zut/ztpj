package pl.rafal.ztpj.utils;


import pl.rafal.ztpj.Server;
import pl.rafal.ztpj.rmi.ValidatorImplementation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;

public class MainServerThread implements Runnable {

    private String name;
    Thread t;

    public MainServerThread(String threadName) {
        this.name = threadName;
        t = new Thread(this, threadName);
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void run() {

        try {
            Server.tokens = new HashMap<>();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("localhost",new ValidatorImplementation());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException me) {
            System.out.println("MalformedURLException " + me);
        }

        Server server = new Server();
        try {
            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
