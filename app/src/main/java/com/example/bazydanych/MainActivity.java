package com.example.bazydanych;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private DataBaseFirma dataBaseFirma;
    private EditText imie;
    private EditText nazwisko;
    private Spinner spinnerStanowisko;
    private Button buttonDodajPracownikaDoBazy;
    private List<Pracownik> pracownicy;
    private ListView listView;
    private ArrayAdapter<Pracownik> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imie = findViewById(R.id.editTextImie);
        nazwisko = findViewById(R.id.editTextNazwisko);
        spinnerStanowisko = findViewById(R.id.spinnerStanowiska);
        buttonDodajPracownikaDoBazy = findViewById(R.id.buttonDodaj);
        listView = findViewById(R.id.listViewPracownicy);
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

       buttonDodajPracownikaDoBazy.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       String imiee = imie.getText().toString();
                       String nazwiskoo = nazwisko.getText().toString();
                       String spinnerr = spinnerStanowisko.getSelectedItem().toString();
                       Pracownik pracownik = new Pracownik(imiee, nazwiskoo, "Polski", "Angielski",4600.0,spinnerr);
                       dodajDaneDoBazy(pracownik);
                   }
               }
       );

       wypiszPracownikow();
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        usunPracownika(pracownicy.get(i));
                    }
                }
        );

    }
    private  void usunPracownika (Pracownik pracownik){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        dataBaseFirma.getDaoPracownicy().usunPracownika(pracownik);
                        pracownicy.remove(pracownik);
                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        arrayAdapter.notifyDataSetChanged();
                                    }
                                }
                        );
                    }
                }
        );
    }
    private  void  wypiszPracownikow(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        pracownicy = dataBaseFirma.getDaoPracownicy().wypiszWszystkichPracwonikow();
                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,pracownicy);
                                        listView.setAdapter(arrayAdapter);
                                    }
                                }
                        );
                    }
                }
        );
    }
    private void dodajDaneDoBazy(Pracownik pracownik){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        dataBaseFirma.getDaoPracownicy().dodajPracownika(pracownik);

                        dataBaseFirma.getDaoPracownicy().dodajPracownika(new Pracownik("Jaś","Nowak","polski", "angielski", 12300.99,"programista"));
                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        pracownicy.add(pracownik);
                                        arrayAdapter.notifyDataSetChanged();
                                        Toast.makeText(MainActivity.this,"Dodano do bazy", Toast.LENGTH_SHORT).show();

                                    }
                                }
                        );
                    }
                }
        );
    }
}