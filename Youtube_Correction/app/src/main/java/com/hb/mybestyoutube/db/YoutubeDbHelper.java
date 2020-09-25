package com.hb.mybestyoutube.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class YoutubeDbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "youtube_video.db";

    // nom de la table
    public static final String YOUTUBE_VIDEO_TABLE_NAME = "YoutubeVideo";

    // déclare les index des colonnes
    public static final int YOUTUBE_VIDEO_KEY_COLUMN_INDEX = 0;
    public static final int YOUTUBE_VIDEO_TITRE_COLUMN_INDEX = 1;
    public static final int YOUTUBE_VIDEO_DESCRIPTION_COLUMN_INDEX = 2;
    public static final int YOUTUBE_VIDEO_URL_COLUMN_INDEX = 3;
    public static final int YOUTUBE_VIDEO_CATEGORIE_COLUMN_INDEX = 4;

    // déclare les colonnes
    public static final String YOUTUBE_VIDEO_KEY = "id";
    public static final String YOUTUBE_VIDEO_TITRE = "titre";
    public static final String YOUTUBE_VIDEO_DESCRIPTION = "description";
    public static final String YOUTUBE_VIDEO_URL = "url";
    public static final String YOUTUBE_VIDEO_CATEGORIE = "categorie";

    // requete de création de la table
    private static final String YOUTUBE_VIDEO_TABLE_CREATE =
            "CREATE TABLE " + YOUTUBE_VIDEO_TABLE_NAME + " (" +
                    YOUTUBE_VIDEO_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    YOUTUBE_VIDEO_TITRE + " TEXT, " +
                    YOUTUBE_VIDEO_DESCRIPTION + " TEXT, " +
                    YOUTUBE_VIDEO_URL + " TEXT, " +
                    YOUTUBE_VIDEO_CATEGORIE + " TEXT);";

    private static final String YOUTUBE_VIDEO_TABLE_DROP = "DROP TABLE IF EXISTS " + YOUTUBE_VIDEO_TABLE_NAME + ";";

    public YoutubeDbHelper(Context context) {

        super(context, DATABASE_NAME, null, VERSION);
    }

    // créé la base et la table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(YOUTUBE_VIDEO_TABLE_CREATE);
    }

    // met à jour la base (pas implémenté)
    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        db.execSQL(YOUTUBE_VIDEO_TABLE_DROP);
        onCreate(db);
    }

}
