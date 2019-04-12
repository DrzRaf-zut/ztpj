package pl.rafal.ztpj.ui;

import pl.rafal.ztpj.Client;
import pl.rafal.ztpj.dao.PracownikDao;
import pl.rafal.ztpj.model.Pracownik;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PobierzDane {

    public void print() {
        //Adres: localhost, Port: 1234
        System.out.println("5. Pobierz dane z sieci");

        Client client = new Client();

        try {
            System.out.print("Podaj użytkownika : ");
            Scanner input = new Scanner(System.in);
            String user = input.nextLine();
            System.out.print("Podaj hasło : ");
            Scanner input2 = new Scanner(System.in);
            String pass = input2.nextLine();

            if(client.authenticate(user,pass)==false){
                throw new AuthenticationException();
            }

            System.out.print("Adres : ");
            String stringInput = "";
            input = new Scanner(System.in);
            stringInput = input.nextLine();
            if (stringInput.equals("")) {stringInput = "localhost";}
            System.out.print("Port : ");
            int intInput = 0;
            input2 = new Scanner(System.in);
            intInput = input2.nextInt();
            if (intInput == 0){ intInput = 1234;}


            List<Pracownik> lista_pobrana;
            lista_pobrana = client.runClient(stringInput,intInput);

            if (lista_pobrana.isEmpty()){
                System.out.println("Token wygasł.");
                throw new AuthenticationException();
            }


            System.out.println("------------------------");

            PracownikDao pracownikDao = new PracownikDao();
            List<Pracownik> lista = pracownikDao.getAll();

            System.out.print("Czy zapisać pobrane dane? [T]/[N] ");

            String stringInput2 = "";
            Scanner input3 = new Scanner(System.in);
            stringInput2 = input3.nextLine();


            switch (stringInput2.toLowerCase()) {
                case "t":
                    for (Pracownik pracownik : lista) {
                        pracownikDao.delete(pracownik);
                    }

                    for (Pracownik pracownik : lista_pobrana) {
                        pracownikDao.save(pracownik);
                    }
                    System.out.println("Zapisano dane");
                    break;
                case "n":
                    System.out.println("Odrzucono dane");
                    break;
                default:
                    System.out.println("Wybierz [T] lub [N]");
                    print();
                    break;
            }
        }catch (NotBoundException e){
            System.out.println("Błąd połączenia serwera");
        } catch (ClassNotFoundException | IOException e){
            System.out.println("Błąd odczytu");
        } catch (AuthenticationException e){
            System.out.println("Błąd autentykacji");
        }

        System.out.println("[ENTER] - powrót do ekranu głównego");

        try {
            Scanner input4 = new Scanner(System.in);
            input4.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Błąd odczytu");
        } finally {
            Menu.print();
        }


    }
}
