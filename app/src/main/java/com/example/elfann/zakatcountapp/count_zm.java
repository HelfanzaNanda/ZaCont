package com.example.elfann.zakatcountapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class count_zm extends AppCompatActivity {

    EditText edtJumlahHarta,  namaPem;
    CheckBox konfirm;
    Button btnHitung,btnReset;
    TextView nishabEmasSaatIni, totalZakat;
    EditText EdtHargaEmas;

    //hukum islamnya, nisabnya 20 nisab = 85 gr murni emas
    final static int nishabPatokan = 85;

    String strJmlHarta, strHargaEmas, strTotalZakat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_zm);

        initView();
        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtJumlahHarta.getText().toString().isEmpty()){
                    edtJumlahHarta.setError("Masukkan Jumlah Harga");
                    return;
                }

                if(EdtHargaEmas.getText().toString().isEmpty()){
                    EdtHargaEmas.setError("Masukkan Harga Emas");
                    return;
                }

                if(!konfirm.isChecked()){

                    return;
                }




                int hargaEmas = Integer.parseInt(EdtHargaEmas.getText().toString());
                int jumlahHarta = Integer.parseInt(edtJumlahHarta.getText().toString());

                int converedtNishab = hargaEmas * nishabPatokan;
                if(jumlahHarta < converedtNishab){
                    AlertDialog.Builder builder = new AlertDialog.Builder(count_zm.this);
                    builder.setTitle("Attention");
                    builder.setMessage(" Anda Belum Wajib Mengeluarkan Zakat Mall \n"+
                        "Harta Anda Belum Mencapai Nishab !");
                    builder.setPositiveButton("Siap", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    builder.show();
                    return;
                }
                //kesepakatan ulama, ketika udah melewati nishab, zakat yg harus di keluarkan
                //adalah 0.025(2,5%)
                long totalZakatReal = jumlahHarta / 40;


                //make formater
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                formatRp.setCurrencySymbol("Rp. ");
                formatRp.setDecimalSeparator('.');
                DecimalFormat mataUangIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                mataUangIndonesia.setDecimalFormatSymbols(formatRp);

                //convert int and long ke formater
                strHargaEmas = mataUangIndonesia.format(converedtNishab);
                strJmlHarta = mataUangIndonesia.format(jumlahHarta);
                strTotalZakat = mataUangIndonesia.format(totalZakatReal);

                //set text hasil

                nishabEmasSaatIni.setText(strHargaEmas);
                totalZakat.setText(strTotalZakat);
            }
        });

    }
    //inisiasi elemet layout
    public void initView(){
        edtJumlahHarta = findViewById(R.id.edtJmlKekayaan);
        EdtHargaEmas = findViewById(R.id.edtHrgEmas);
        konfirm = findViewById(R.id.cbConfirm);
        btnHitung = findViewById(R.id.btnHitungZakatMal);
        btnReset = findViewById(R.id.btnresetZmal);
        nishabEmasSaatIni = findViewById(R.id.txtNishabEmas);
        totalZakat = findViewById(R.id.txtTotalZMal);
        namaPem = findViewById(R.id.edtNp);

    }
}
