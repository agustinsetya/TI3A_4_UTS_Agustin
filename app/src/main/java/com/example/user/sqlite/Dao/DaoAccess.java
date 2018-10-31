package com.example.user.sqlite.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.user.sqlite.model.Kota;

import java.util.List;
@Dao
public interface DaoAccess {
    @Query("SELECT * FROM Kota")
    List<Kota> getAll();


    @Insert
    void insertAll(Kota... kotas);
}
