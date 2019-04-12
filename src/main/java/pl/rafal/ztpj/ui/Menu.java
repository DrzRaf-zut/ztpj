package pl.rafal.ztpj.ui;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu  implements UI {
    public static void print() {
        System.out.println("MENU");
        System.out.println("1. Lista pracowników");
        System.out.println("2. Dodaj pracownika");
        System.out.println("3. Usuń pracownika");
        System.out.println("4. Kopia zapasowa");
        System.out.println("5. Pobierz dane z sieci");
        System.out.println("\n0. Wyjście z programu");
        System.out.print("Wybór>");
        try{
            Scanner input = new Scanner(System.in);
            int intInput = input.nextInt();
            switch (intInput) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    ListaPracownikow lista = new ListaPracownikow();
                    lista.print();
                    break;
                case 2:
                    DodajPracownika dodaj = new DodajPracownika();
                    dodaj.print();
                    break;
                case 3:
                    UsunPracownika usun = new UsunPracownika();
                    usun.print();
                    break;
                case 4:
                    KopiaZapasowa kopia = new KopiaZapasowa();
                    kopia.print();
                    break;
                case 5:
                    PobierzDane pobierz = new PobierzDane();
                    pobierz.print();
                    break;
                default:
                    System.out.println("Podaj liczbę z zakresu 0-5");
                    Menu.print();
                    break;
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Podaj liczbę");
            Menu.print();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
