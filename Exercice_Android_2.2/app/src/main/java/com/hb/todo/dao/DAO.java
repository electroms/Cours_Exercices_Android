package com.hb.todo.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO {

    protected SQLiteDatabase db = null;
    protected SQLiteOpenHelper baseHelper = null;

    public DAO(SQLiteOpenHelper baseHelper) {
        this.baseHelper = baseHelper;
    }

    // créé la première fois la bdd et ouvre ensuite la bdd
    public SQLiteDatabase open() {
        db = baseHelper.getWritableDatabase();
        return db;
    }

    // ferme la base de données
    public void close() {
        if (db != null) {
            db.close();
        }
    }

    // retourne l'objet db
    public SQLiteDatabase getDb() {
        return db;
    }
}
