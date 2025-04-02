package com.example.bazydanych;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "pracownicyKlasy3A")
public class Pracownik {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_pracownika")
    private  int idPracowinka;
    @ColumnInfo(name = "imie_pracownika")
    private String imie;
    private String nazwisko;
    private String jezykOjczysty;
    private String jezykObcy;
    private double wynagrodzenie;
    private String stanowisko;
    @Ignore
    public Pracownik() {

    }

    public Pracownik(String imie, String nazwisko, String jezykOjczysty, String jezykObcy, double wynagrodzenie, String stanowisko) {

        this.idPracowinka = 0;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.jezykOjczysty = jezykOjczysty;
        this.jezykObcy = jezykObcy;
        this.wynagrodzenie = wynagrodzenie;
        this.stanowisko = stanowisko;
    }

    public int getIdPracowinka() {
        return idPracowinka;
    }

    public void setIdPracowinka(int idPracowinka) {
        this.idPracowinka = idPracowinka;
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

    public String getJezykOjczysty() {
        return jezykOjczysty;
    }

    public void setJezykOjczysty(String jezykOjczysty) {
        this.jezykOjczysty = jezykOjczysty;
    }

    public String getJezykObcy() {
        return jezykObcy;
    }

    public void setJezykObcy(String jezykObcy) {
        this.jezykObcy = jezykObcy;
    }

    public double getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setWynagrodzenie(double wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    @Override
    public String toString() {
        return
                "imie: '" + imie + '\'' +
                ", nazwisko: '" + nazwisko + '\'' +
                ", stanowisko: '" + stanowisko
                ;
    }
}
