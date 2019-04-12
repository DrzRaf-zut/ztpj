package pl.rafal.ztpj.ui;

import pl.rafal.ztpj.dao.PracownikDao;
import pl.rafal.ztpj.model.Dyrektor;
import pl.rafal.ztpj.model.Handlowiec;
import pl.rafal.ztpj.model.Pracownik;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UsunPracownika implements UI {

    private PracownikDao pracownikDao = new PracownikDao();
    private List<Pracownik> lista = pracownikDao.getAll();

    public void print() {
        System.out.println("3. Usuń pracownika");
        System.out.print("Podaj identyfikator PESEL : ");
        if (lista.isEmpty()) {
            System.out.println("\nBrak rekordów w bazie");
            Menu.print();
        } else {
            try {
                Scanner input = new Scanner(System.in);
                Long longInput = input.nextLong();
                int y = 0;
                for (Pracownik pracownik : lista) {
                    if (pracownik.getPesel().equals(longInput)) {
                        System.out.println("\n------------------------");
                        if (pracownik instanceof Dyrektor) {
                            Dyrektor dyrektor = (Dyrektor) pracownik;
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
                        System.out.println("[Enter] – potwierdź");
                        System.out.println("[Q] – porzuć");
                        try {
                            int x = 0;
                            while (x == 0) {
                                x = 0;
                                Scanner input2 = new Scanner(System.in);
                                String stringInput = input2.nextLine();
                                switch (stringInput.toLowerCase()) {
                                    case "q":
                                        Menu.print();
                                        x += 1;
                                        break;
                                    case "":
                                        pracownikDao.delete(pracownik);
                                        System.out.println("Usunięto pracownika\n");
                                        x += 1;
                                        break;
                                    default:
                                        System.out.println("Wybierz Enter lub Q");
                                        print();
                                        break;
                                }
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Błąd odczytu");
                            Menu.print();
                        }
                        break;
                    }
                    y += 1;
                }
                if (y == lista.size()) {
                    System.out.println("\nNie odnaleziono pracownika o podanym nr PESEL\n");
                }

            } catch (InputMismatchException e) {
                System.out.println("Błąd odczytu");
                Menu.print();
            }
            Menu.print();
        }
    }

}
