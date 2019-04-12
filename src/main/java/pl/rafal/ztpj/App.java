package pl.rafal.ztpj;

import pl.rafal.ztpj.ui.Menu;
import pl.rafal.ztpj.utils.MainServerThread;

public class App {
    public static void main(String[] args) {

        MainServerThread serverThread = new MainServerThread("serverThread");

        Menu.print();
    }
}
