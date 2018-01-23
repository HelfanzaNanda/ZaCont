package com.example.elfann.zakatcountapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;

import com.example.elfann.zakatcountapp.adapter.Zf_Adapter;
import com.example.elfann.zakatcountapp.adapter.Zf_Adapter;
import com.example.elfann.zakatcountapp.model.Mdl_Zf;

import java.util.ArrayList;

public class List_Zf extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fabAdd;
    Db_Helper db_helper;
    ArrayList<Mdl_Zf> listTangkap;
    Zf_Adapter adapter;

    public static List_Zf activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__zf);

        recyclerView = findViewById(R.id.recyclerView);
        fabAdd = findViewById(R.id.floatingAdd);
        activity = this;




        //activate back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Tangkap data dari database
        db_helper = new Db_Helper(List_Zf.this);
        listTangkap = db_helper.getAllDataFitr();

        //inisiasi adapter
        adapter = new Zf_Adapter(listTangkap, List_Zf.this);

        //setting layout manager dan set adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(List_Zf. this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(List_Zf.this, Count_Zf.class));
                finish();
            }
        });
    }


    public void deleteFromList(Mdl_Zf mdl_zf){
        db_helper = new Db_Helper(List_Zf.this);
        //delete dari db
        db_helper.deleteDataZfitr(mdl_zf);

        //delete dari list
        listTangkap.remove(mdl_zf);
        //kabari adapter bahwa data sudah berubah
        adapter.notifyDataSetChanged();
        Snackbar.make(recyclerView, "Data Berhasil Dihapus", Snackbar.LENGTH_LONG).show();
    }
}
