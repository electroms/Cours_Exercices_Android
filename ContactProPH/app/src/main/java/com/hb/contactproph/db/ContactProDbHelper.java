package com.hb.contactproph.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactProDbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "contactPro.db";

    public static final String CONTACTPRO_TABLE_NAME = "ContactPro";

    public static final int CONTACTPRO_KEY_COLUMN_INDEX = 0;
    public static final int CONTACTPRO_NOM_COLUMN_INDEX = 1;
    public static final int CONTACTPRO_PRENOM_COLUMN_INDEX = 2;
    public static final int CONTACTPRO_SOCIETE_COLUMN_INDEX = 3;
    public static final int CONTACTPRO_ADRESSE_COLUMN_INDEX = 4;
    public static final int CONTACTPRO_TEL_COLUMN_INDEX = 5;
    public static final int CONTACTPRO_MAIL_COLUMN_INDEX = 6;
    public static final int CONTACTPRO_WEBSITE_COLUMN_INDEX = 7;
    public static final int CONTACTPRO_SECTEUR_COLUMN_INDEX = 8;

    public static final String CONTACTPRO_KEY = "id";
    public static final String CONTACTPRO_NOM = "nom";
    public static final String CONTACTPRO_PRENOM = "prenom";
    public static final String CONTACTPRO_SOCIETE = "societe";
    public static final String CONTACTPRO_ADRESSE = "adresse";
    public static final String CONTACTPRO_TEL = "tel";
    public static final String CONTACTPRO_MAIL = "mail";
    public static final String CONTACTPRO_WEBSITE = "website";
    public static final String CONTACTPRO_SECTEUR = "secteur";

    private static final String CONTACTPRO_TABLE_CREATE =
            "CREATE TABLE " + CONTACTPRO_TABLE_NAME + " (" +
                    CONTACTPRO_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CONTACTPRO_NOM + " TEXT, " +
                    CONTACTPRO_PRENOM + " TEXT, " +
                    CONTACTPRO_SOCIETE + " TEXT, " +
                    CONTACTPRO_ADRESSE + " TEXT," +
                    CONTACTPRO_TEL + " TEXT," +
                    CONTACTPRO_MAIL + " TEXT," +
                    CONTACTPRO_WEBSITE + " TEXT," +
                    CONTACTPRO_SECTEUR + " TEXT);";

    private static final String CONTACTPRO_TABLE_DROP = "DROP TABLE IF EXISTS " + CONTACTPRO_TABLE_NAME + ";";

    public ContactProDbHelper(Context context) {

        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CONTACTPRO_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        db.execSQL(CONTACTPRO_TABLE_DROP);
        onCreate(db);
    }


}
