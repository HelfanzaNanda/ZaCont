package com.example.elfann.zakatcountapp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elfann.zakatcountapp.model.Mdl_Zf;

public class Count_Zf extends AppCompatActivity {

    EditText edtJumlahKel, edtNamaKepKel;
    Button hitung, reset;
    TextView textTotalZakat;
    LinearLayout linearHasil;

    int jumlahKel;
    static final int TAKARAN = 3;
    int totalZakat = 0;
    String nKepKel;
    Db_Helper db_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count__zf);
        edtJumlahKel = (EditText)findViewById(R.id.edtJumlahKeluarga);
        edtNamaKepKel = (EditText)findViewById(R.id.edtkepalaKeluarga);
        hitung= (Button)findViewById(R.id.btnHitungZakatFitr);
        reset = (Button)findViewById(R.id.btnResetZfitr);
        textTotalZakat = (TextView) findViewById(R.id.txtTotalZakatF);
        linearHasil = (LinearLayout)findViewById(R.id.linearHasil);
        linearHasil.setVisibility(View.GONE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //menampilkan back (belom ada aksi)


        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtJumlahKel.getText().toString().isEmpty()){
                    edtJumlahKel.setError("Masukkan Jumlah Keluarga");
                    return;
                }

                String strJumlahKel = edtJumlahKel.getText().toString(); //mengubah menjadi string
                jumlahKel = Integer.parseInt(strJumlahKel); //parse int mengubah menjadi int

                totalZakat = jumlahKel * TAKARAN;
                textTotalZakat.setText(String.valueOf(totalZakat)); //value fo mengubah int ke string
                linearHasil.setVisibility(View.VISIBLE);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtJumlahKel.setText("");
                edtNamaKepKel.setText("");
                linearHasil.setVisibility(View.INVISIBLE);
            }
        });


    }

    //METHOD BUAT AKSI DI TOOLBAR
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get item id
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        if(id == R.id.itemSave){

            if (totalZakat == 0) {
                Toast.makeText(this, "Silahkan Hitung Dahulu", Toast.LENGTH_SHORT).show();
                return false;
            }
            if ((edtNamaKepKel.getText().toString().isEmpty())){
                edtNamaKepKel.setError("Masukkan Nama KK");
                return false;
            }

            db_helper = new Db_Helper(Count_Zf.this);
            Mdl_Zf temp = new Mdl_Zf();
            temp.setJumlahKel(jumlahKel);
            temp.setTotalZakat(totalZakat);
            temp.setNamaKK(edtNamaKepKel.getText().toString());
            db_helper.saveDataZF(temp);
            Snackbar.make(linearHasil, "Data Berhasil Disimpan", Snackbar.LENGTH_SHORT).show();
        }
        return true;
    }

    //BUAT INFLATE TOOLBAR MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }
}
