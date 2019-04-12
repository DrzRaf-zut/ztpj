package pl.rafal.ztpj.ui;

import pl.rafal.ztpj.dao.PracownikDao;
import pl.rafal.ztpj.model.Dyrektor;
import pl.rafal.ztpj.model.Handlowiec;
import pl.rafal.ztpj.model.Pracownik;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ListaPracownikow implements UI {

    private PracownikDao pracownikDao = new PracownikDao();
    private List<Pracownik> lista = pracownikDao.getAll();

    public void print() {
        if (lista.isEmpty()) {
            System.out.println("Brak rekordów w bazie");
            Menu.print();
        } else
            print(lista.get(0), 1, lista.size());
    }

    private void print(Pracownik pracownik, int nr, int lista_length) {
        if (pracownik instanceof Dyrektor) {
            Dyrektor dyrektor = (Dyrektor) pracownik;
            System.out.println("1. Lista pracowników\n");
            System.out.println("Identyfikator PESEL : " + dyrektor.getPesel());
            System.out.println("Imię : " + dyrektor.getImie());
            System.out.println("Nazwisko : " + dyrektor.getNazwisko());
            System.out.println("Stanowisko : Dyrektor");
            System.out.println("Wynagrodzenie (zł) : " + dyrektor.getWynagrodzenie());
            System.out.println("Telefon służbowy numer : " + dyrektor.getTelefon());
            System.out.println("Dodatek służbowy (zł) : " + dyrektor.getDodatek_sluzbowy());
            System.out.println("Karta służbowa numer : " + dyrektor.getKarta_sluzbowa());
            System.out.println("Limit kosztów/miesiąc (zł) : " + dyrektor.getLimit_kosztow());
        } else if (pracownik instanceof Handlowiec) {
            Handlowiec handlowiec = (Handlowiec) pracownik;
            System.out.println("1. Lista pracowników\n");
            System.out.println("Identyfikator PESEL : " + handlowiec.getPesel());
            System.out.println("Imię : " + handlowiec.getImie());
            System.out.println("Nazwisko : " + handlowiec.getNazwisko());
            System.out.println("Stanowisko : Handlowiec");
            System.out.println("Wynagrodzenie (zł) : " + handlowiec.getWynagrodzenie());
            System.out.println("Telefon służbowy numer : " + handlowiec.getTelefon());
            System.out.println("Prowizja (%) : " + handlowiec.getProwizja());
            System.out.println("Limit prowizji/miesiąc (zł) : " + handlowiec.getLimit_prowizji());
        } else {
            throw new IllegalArgumentException();
        }
        System.out.println("[Pozycja: " + nr + "/" + lista_length + "]");
        System.out.println("[Enter] – następny");
        System.out.println("[Q] – powrót");
        try {
            int x = 0;
            while (x == 0) {
                x=0;
                Scanner input = new Scanner(System.in);
                String stringInput = input.nextLine();
                switch (stringInput.toLowerCase()) {
                    case "q":
                        Menu.print();
                        x+=1;
                        break;
                    case "":
                        nr += 1;
                        if (nr > lista_length)
                            nr = 1;
                        print(lista.get(nr - 1), nr, lista_length);
                        x+=1;
                        break;
                    default:
                        System.out.println("Wybierz Enter lub Q");
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Błąd odczytu");
            Menu.print();
        }


    }
}



