package com.example.user.sqlite;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.sqlite.DB.Database;
import com.example.user.sqlite.DB.KotaDB;
import com.example.user.sqlite.model.Kota;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MyAdapter adapter;
    private TextView textHasil;
    Button buttonBack;
    private List<Kota> kotaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        kotaList = new ArrayList<>();
        final KotaDB db = Room.databaseBuilder(getApplicationContext(), KotaDB.class, "db_kota")
                .allowMainThreadQueries().build();

        initComponents();
        recyclerView = (RecyclerView) findViewById(R.id.rv_kota);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        db = new Database(ListActivity.this);
        kotaList = db.daoAccess().getAll();
        adapter = new MyAdapter(kotaList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        cekDataRecyclerView();

        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, InputKota.class);
                startActivity(intent);
            }
        });
    }

    private void initComponents(){
        textHasil = (TextView) findViewById(R.id.textHasil);
    }

    // FUNGSI INI UNTUK MENGECEK APAKAH ADA DATA DI DALEM RECYCLERVIEW ATAU TIDAK
    private void cekDataRecyclerView() {
        if (adapter.getItemCount() == 0) {
            textHasil.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            textHasil.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            recyclerView.addOnItemTouchListener(
                    new RVItemClick(getApplicationContext(), new RVItemClick.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            // TODO Handle item click
                            Kota kota = kotaList.get(position);
                            String nama = kota.getKota();

                            Toast.makeText(ListActivity.this, "Klik di " + nama, Toast.LENGTH_SHORT).show();
                        }
                    })
            );
        }
    }
}
