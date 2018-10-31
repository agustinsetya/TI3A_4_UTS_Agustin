package com.example.user.sqlite.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.user.sqlite.Dao.DaoAccess;
import com.example.user.sqlite.model.Kota;

@Database(entities = {Kota.class}, version = 1, exportSchema = false)
public abstract class KotaDB extends RoomDatabase {

    public abstract DaoAccess daoAccess();
}
