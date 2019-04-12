package pl.rafal.ztpj.model;

import java.io.Serializable;

public abstract class Pracownik implements Serializable {

    private int id;
    private Long pesel;
    private String imie;
    private String nazwisko;
    private int wynagrodzenie;
    private String telefon;
    private String stanowisko;

    public Pracownik(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getPesel() {
        return pesel;
    }

    public void setPesel(long pesel) {
        this.pesel = pesel;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setWynagrodzenie(int wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }
}

