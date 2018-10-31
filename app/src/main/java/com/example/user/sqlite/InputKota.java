package com.example.user.sqlite;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.sqlite.DB.Database;
import com.example.user.sqlite.DB.KotaDB;
import com.example.user.sqlite.model.Kota;

import java.util.List;

public class InputKota extends AppCompatActivity {
    EditText kota;
    Button simpan, cek;
//    KotaDB db;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_kota);

        final KotaDB db = Room.databaseBuilder(getApplicationContext(), KotaDB.class, "db_kota")
                .allowMainThreadQueries().build();

        kota=findViewById(R.id.editKota);
        simpan=findViewById(R.id.buttonSave);
        cek=findViewById(R.id.buttonCek);


        cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InputKota.this, ListActivity.class));
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kota.getText().toString().isEmpty()){
                    kota.setError("Nama Kota Kosong");
                    kota.requestFocus();
                }else{
                    Kota nmKota = new Kota(kota.getText().toString());
                    db.daoAccess().insertAll(nmKota);
                    kota.setText("");
                    List<Kota> kotaList = db.daoAccess().getAll();
                    adapter = new MyAdapter(kotaList);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(InputKota.this, "Berhasil Menambahkan Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initComponents(){

    }
}
