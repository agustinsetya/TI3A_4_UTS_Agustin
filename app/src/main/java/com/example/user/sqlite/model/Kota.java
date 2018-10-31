package com.example.user.sqlite.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Kota implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String kota;

    public Kota(String kota) {
        this.kota = kota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }
}
