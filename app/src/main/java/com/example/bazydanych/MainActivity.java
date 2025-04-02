package com.example.bazydanych;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private DataBaseFirma dataBaseFirma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RoomDatabase.Callback mojCallBack = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                super.onDestructiveMigration(db);
            }
        };
        dataBaseFirma = Room.databaseBuilder(
                getApplicationContext(),DataBaseFirma.class,"PracownicyDB").addCallback(mojCallBack)
                .allowMainThreadQueries()
                .build();

        dodajDaneDoBazy();
    }
    private void dodajDaneDoBazy(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        dataBaseFirma.getDaoPracownicy().dodajPracownika(new Pracownik("Jaś","Nowak","polski", "angielski", 12300.99,"programista"));
                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,"Dodano do bazy", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                    }
                }
        );
    }
}