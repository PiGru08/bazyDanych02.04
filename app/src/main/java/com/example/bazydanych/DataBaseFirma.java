package com.example.bazydanych;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Pracownik.class}, version = 2, exportSchema = false)
public abstract class DataBaseFirma extends RoomDatabase {
    public abstract DaoPracownicy getDaoPracownicy();
}
