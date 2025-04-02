package com.example.bazydanych;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DaoPracownicy {
    @Insert
    public void dodajPracownika(Pracownik pracownik);

    @Insert
    public void dodajWieluPracownikow(Pracownik ...pracownicy);

    @Delete
    public void usunPracownika(Pracownik pracownik);

    @Update
    public void zaktualizujDanePracownika(Pracownik pracownik);
    @Query("Select * from PRACOWNICYKLASY3A")
    public List<Pracownik> wypiszWszystkichPracwonikow();

    /*@Query("Select * from pracownicyKlasy3A where jezykOjczysty = 'polski'")
    public List<Pracownik> wypiszPracownikowPolskoJezycznych();

    @Query("Select * from pracownicyKlasy3A where jezykObcy = :jezyk")
    public List<Pracownik> wypiszPracownikowMowiacychJezykiem(String jezyk);
    */
}
