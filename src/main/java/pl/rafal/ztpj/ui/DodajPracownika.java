package pl.rafal.ztpj.ui;

import pl.rafal.ztpj.dao.PracownikDao;
import pl.rafal.ztpj.model.Dyrektor;
import pl.rafal.ztpj.model.Handlowiec;
import pl.rafal.ztpj.model.StanowiskoE;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DodajPracownika implements UI {

    private PracownikDao pracownikDao = new PracownikDao();

    public void print() {
        System.out.println("2. Dodaj pracownika");
        System.out.print("[D]yrektor/[H]andlowiec : ");
        try {
            Scanner input = new Scanner(System.in);
            String stringInput = input.nextLine();
            switch (stringInput.toLowerCase()) {
                case "d":
                    Dyrektor dyrektor = new Dyrektor();
                    dyrektor.setStanowisko(StanowiskoE.DYREKTOR.name());
                    System.out.println("2. Dodaj pracownika");
                    System.out.println("[D]yrektor/[H]andlowiec : Dyrektor");
                    System.out.println("\n------------------------");
                    System.out.print("Identyfikator PESEL : ");
                    Scanner in = new Scanner(System.in);
                    Long l = in.nextLong();
                    dyrektor.setPesel(l);
                    System.out.print("\nImię : ");
                    in = new Scanner(System.in);
                    String s = in.nextLine();
                    dyrektor.setImie(s);
                    System.out.print("\nNazwisko : ");
                    in = new Scanner(System.in);
                    s = in.nextLine();
                    dyrektor.setNazwisko(s);
                    System.out.println("\nWynagrodzenie (zł) : ");
                    in = new Scanner(System.in);
                    int intInput = in.nextInt();
                    dyrektor.setWynagrodzenie(intInput);
                    System.out.print("\nTelefon : ");
                    in = new Scanner(System.in);
                    s = in.nextLine();
                    dyrektor.setTelefon(s);
                    System.out.println("\nDodatek słuzbowy (zł) : ");
                    in = new Scanner(System.in);
                    intInput = in.nextInt();
                    dyrektor.setDodatek_sluzbowy(intInput);
                    System.out.print("\nKarta służbowa numer : ");
                    in = new Scanner(System.in);
                    s = in.nextLine();
                    dyrektor.setKarta_sluzbowa(s);
                    System.out.println("\nLimit kosztów/miesiąc (zł) : ");
                    in = new Scanner(System.in);
                    intInput = in.nextInt();
                    dyrektor.setLimit_kosztow(intInput);

                    System.out.println("\n\n2. Dodaj pracownika");
                    System.out.print("[D]yrektor/[H]andlowiec : Dyrektor");
                    System.out.println("\n------------------------");
                    System.out.println("Imię : " + dyrektor.getImie());
                    System.out.println("Nazwisko : " + dyrektor.getNazwisko());
                    System.out.println("Stanowisko : " + dyrektor.getStanowisko());
                    System.out.println("Wynagrodzenie (zł) : " + dyrektor.getWynagrodzenie());
                    System.out.println("Telefon służbowy numer : " + dyrektor.getTelefon());
                    System.out.println("Dodatek służbowy (zł) : " + dyrektor.getDodatek_sluzbowy());
                    System.out.println("Karta służbowa numer : " + dyrektor.getKarta_sluzbowa());
                    System.out.println("Limit kosztów/miesiąc (zł) : " + dyrektor.getLimit_kosztow());
                    System.out.println("\n------------------------");
                    System.out.println("[Enter] – potwierdź");
                    System.out.println("[Q] – porzuć");

                    input = new Scanner(System.in);
                    stringInput = input.nextLine();
                    switch (stringInput.toLowerCase()) {
                        case "q":
                            Menu.print();
                            break;
                        case "":
                            pracownikDao.save(dyrektor);
                            System.out.println("dodano");
                            break;
                        default:
                            System.out.println("Wybierz Enter lub Q");
                            break;
                    }
                    Menu.print();
                    break;
                case "h":
                    Handlowiec handlowiec = new Handlowiec();
                    handlowiec.setStanowisko(StanowiskoE.HANDLOWIEC.name());
                    System.out.println("2. Dodaj pracownika");
                    System.out.println("[D]yrektor/[H]andlowiec : Handlowiec");
                    System.out.println("\n------------------------");
                    System.out.print("Identyfikator PESEL : ");
                    in = new Scanner(System.in);
                    l = in.nextLong();
                    handlowiec.setPesel(l);
                    System.out.print("\nImię : ");
                    in = new Scanner(System.in);
                    s = in.nextLine();
                    handlowiec.setImie(s);
                    System.out.print("\nNazwisko : ");
                    in = new Scanner(System.in);
                    s = in.nextLine();
                    handlowiec.setNazwisko(s);
                    System.out.println("\nWynagrodzenie (zł) : ");
                    in = new Scanner(System.in);
                    intInput = in.nextInt();
                    handlowiec.setWynagrodzenie(intInput);
                    System.out.print("\nTelefon : ");
                    in = new Scanner(System.in);
                    s = in.nextLine();
                    handlowiec.setTelefon(s);
                    System.out.println("\nProwizja (%) : ");
                    in = new Scanner(System.in);
                    intInput = in.nextInt();
                    handlowiec.setProwizja(intInput);
                    System.out.println("\nLimit prowizji/miesiąc (zł) : ");
                    in = new Scanner(System.in);
                    intInput = in.nextInt();
                    handlowiec.setLimit_prowizji(intInput);

                    System.out.println("\n\n2. Dodaj pracownika");
                    System.out.print("[D]yrektor/[H]andlowiec : Handlowiec");
                    System.out.println("\n------------------------");
                    System.out.println("Imię : " + handlowiec.getImie());
                    System.out.println("Nazwisko : " + handlowiec.getNazwisko());
                    System.out.println("Stanowisko : " + handlowiec.getStanowisko());
                    System.out.println("Wynagrodzenie (zł) : " + handlowiec.getWynagrodzenie());
                    System.out.println("Telefon służbowy numer : " + handlowiec.getTelefon());
                    System.out.println("Prowizja (%) : " + handlowiec.getProwizja());
                    System.out.println("Limit prowizji/miesiąc (zł) : " + handlowiec.getLimit_prowizji());
                    System.out.println("\n------------------------");
                    System.out.println("[Enter] – potwierdź");
                    System.out.println("[Q] – porzuć");

                    input = new Scanner(System.in);
                    stringInput = input.nextLine();
                    switch (stringInput.toLowerCase()) {
                        case "q":
                            Menu.print();
                            break;
                        case "":
                            pracownikDao.save(handlowiec);
                            System.out.println("Dodano pracownika\n");
                            break;
                        default:
                            System.out.println("Wybierz Enter lub Q");
                            break;
                    }
                    Menu.print();
                    break;
                default:
                    Menu.print();
                    break;
            }

        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }
}