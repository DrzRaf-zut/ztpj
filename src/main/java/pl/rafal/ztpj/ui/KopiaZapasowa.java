package pl.rafal.ztpj.ui;

import pl.rafal.ztpj.dao.PracownikDao;
import pl.rafal.ztpj.model.Pracownik;
import pl.rafal.ztpj.utils.Compress;
import pl.rafal.ztpj.utils.Decompress;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class KopiaZapasowa {

    public static final String PATH = "output/";
    private PracownikDao pracownikDao = new PracownikDao();
    private List<Pracownik> lista = pracownikDao.getAll();

    public void print() throws ClassNotFoundException {
        System.out.println("4. Kopia zapasowa");
        System.out.print("[Z]achowaj/[O]dtwórz : ");
        String stringInput = "";
        try {
            Scanner input = new Scanner(System.in);
            stringInput = input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Błąd odczytu");
            Menu.print();
        }
        System.out.println("------------------------");
        System.out.print("Kompresja [G]zip/[Z]ip : ");
        String stringInput2 = "";
        try {
            Scanner input2 = new Scanner(System.in);
            stringInput2 = input2.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Błąd odczytu");
            Menu.print();
        }
        System.out.print("Nazwa pliku : ");
        String stringInput3 = "";
        try {
            Scanner input3 = new Scanner(System.in);
            stringInput3 = input3.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Błąd odczytu");
            Menu.print();
        }

        switch (stringInput.toLowerCase()) {
            case "z":
                System.out.println("------------------------");
                System.out.println("[Enter] – potwierdź");
                System.out.println("[Q] – porzuć");
                try {
                    int x = 0;
                    while (x == 0) {
                        x = 0;
                        Scanner input4 = new Scanner(System.in);
                        String stringInput4 = input4.nextLine();
                        switch (stringInput4.toLowerCase()) {
                            case "q":
                                Menu.print();
                                x += 1;
                                break;
                            case "":
                                pracownikDao.writeToFile(lista,PATH + "Pracownicy.bin");
                                if (stringInput2.toLowerCase().equals("g")) {
                                    Compress.compress_gzip(PATH + "Pracownicy.bin", PATH + stringInput3 + ".gzip");
                                } else if (stringInput2.toLowerCase().equals("z")) {
                                    Compress.compress_zip(PATH + "Pracownicy.bin", PATH + stringInput3 + ".zip");
                                } else {
                                    System.out.println("Wybierz kompresję G lub Z");
                                }
                                System.out.println("Zapisano dane do pliku\n");
                                x += 1;
                                break;
                            default:
                                System.out.println("Wybierz Enter lub Q");
                                print();
                                break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "o":
                System.out.println("------------------------");
                System.out.println("[Enter] – potwierdź");
                System.out.println("[Q] – porzuć");
                try {
                    int x = 0;
                    while (x == 0) {
                        x = 0;
                        Scanner input4 = new Scanner(System.in);
                        String stringInput4 = input4.nextLine();
                        switch (stringInput4.toLowerCase()) {
                            case "q":
                                Menu.print();
                                x += 1;
                                break;
                            case "":
                                if (stringInput2.toLowerCase().equals("g")) {
                                    Decompress.decompress_gzip(PATH + stringInput3 + ".gzip", PATH + "Pracownicy.bin");
                                } else if (stringInput2.toLowerCase().equals("z")) {
                                    Decompress.decompress_zip(PATH + stringInput3 + ".zip", PATH);
                                } else {
                                    System.out.println("Wybierz dekompresję G lub Z");
                                }

                                for (Pracownik pracownik : lista) {
                                    pracownikDao.delete(pracownik);
                                }

                                List<Pracownik> lista_odczyt = pracownikDao.readFile(PATH + "Pracownicy.bin");

                                for (Pracownik pracownik : lista_odczyt) {
                                    pracownikDao.save(pracownik);
                                }
                                System.out.println("Wczytano dane");
                                x += 1;
                                break;
                            default:
                                System.out.println("Wybierz Enter lub Q");
                                print();
                                break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                Menu.print();
                break;
        }
        Menu.print();
    }

}

