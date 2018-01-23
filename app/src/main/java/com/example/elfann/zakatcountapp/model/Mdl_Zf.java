package com.example.elfann.zakatcountapp.model;

/**
 * Created by Elfan N on 21/01/2018.
 */

public class Mdl_Zf {
    private int id, jumlahKel, totalZakat;
    private String namaKK;


    public int getId() {
        return id;
    }

    public int getJumlahKel() {
        return jumlahKel;
    }

    public int getTotalZakat() {
        return totalZakat;
    }

    public String getNamaKK() {
        return namaKK;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJumlahKel(int jumlahKel) {
        this.jumlahKel = jumlahKel;
    }

    public void setTotalZakat(int totalZakat) {
        this.totalZakat = totalZakat;
    }

    public void setNamaKK(String namaKK) {
        this.namaKK = namaKK;
    }
}
