package com.hb.mybestyoutube.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.hb.mybestyoutube.db.YoutubeDbHelper;
import com.hb.mybestyoutube.pojos.YoutubeVideo;

import java.util.ArrayList;
import java.util.List;

public class YoutubeVideoDAO extends DAO {

    public YoutubeVideoDAO(Context context) {
        super(new YoutubeDbHelper(context));
    }

    // récupère un objet YoutubeVideo à partir de son id
    public YoutubeVideo find(Long id) {
        YoutubeVideo youtubeVideo = null;

        Cursor cursor = db.rawQuery("select * from " + YoutubeDbHelper.YOUTUBE_VIDEO_TABLE_NAME +
                        " where " + YoutubeDbHelper.YOUTUBE_VIDEO_KEY + " = ?",
                new String[] { String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()) {
            youtubeVideo = new YoutubeVideo();

            youtubeVideo.setId(Long.parseLong(cursor.getString(YoutubeDbHelper.YOUTUBE_VIDEO_KEY_COLUMN_INDEX)));
            youtubeVideo.setTitre(cursor.getString(YoutubeDbHelper.YOUTUBE_VIDEO_TITRE_COLUMN_INDEX));
            youtubeVideo.setDescription(cursor.getString(YoutubeDbHelper.YOUTUBE_VIDEO_DESCRIPTION_COLUMN_INDEX));
            youtubeVideo.setUrl(cursor.getString(YoutubeDbHelper.YOUTUBE_VIDEO_URL_COLUMN_INDEX));
            youtubeVideo.setCategorie(cursor.getString(YoutubeDbHelper.YOUTUBE_VIDEO_CATEGORIE_COLUMN_INDEX));

            cursor.close();
        }

        return youtubeVideo;
    }

    // Ajoute un objet YoutubeVideo
    public YoutubeVideo add(YoutubeVideo youtubeVideo) {

        open();

        ContentValues value = new ContentValues();

        value.put(YoutubeDbHelper.YOUTUBE_VIDEO_TITRE, youtubeVideo.getTitre());
        value.put(YoutubeDbHelper.YOUTUBE_VIDEO_DESCRIPTION, youtubeVideo.getDescription());
        value.put(YoutubeDbHelper.YOUTUBE_VIDEO_URL, youtubeVideo.getUrl());
        value.put(YoutubeDbHelper.YOUTUBE_VIDEO_CATEGORIE, youtubeVideo.getCategorie());

        long id = db.insert(YoutubeDbHelper.YOUTUBE_VIDEO_TABLE_NAME, null, value);

        // récupère l'id généré et met à jour l'objet YoutubeVideo qui a été ajouté
        youtubeVideo.setId(id);

        return youtubeVideo;
    }

    // récupère une liste d'objets YoutubeVideo
    public List<YoutubeVideo> list() {
        open();

        List<YoutubeVideo> youtubeVideos = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " +
                YoutubeDbHelper.YOUTUBE_VIDEO_TABLE_NAME, null);

        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                YoutubeVideo youtubeVideo = new YoutubeVideo();

                youtubeVideo.setId(Long.parseLong(cursor.getString(YoutubeDbHelper.YOUTUBE_VIDEO_KEY_COLUMN_INDEX)));
                youtubeVideo.setTitre(cursor.getString(YoutubeDbHelper.YOUTUBE_VIDEO_TITRE_COLUMN_INDEX));
                youtubeVideo.setDescription(cursor.getString(YoutubeDbHelper.YOUTUBE_VIDEO_DESCRIPTION_COLUMN_INDEX));
                youtubeVideo.setUrl(cursor.getString(YoutubeDbHelper.YOUTUBE_VIDEO_URL_COLUMN_INDEX));
                youtubeVideo.setCategorie(cursor.getString(YoutubeDbHelper.YOUTUBE_VIDEO_CATEGORIE_COLUMN_INDEX));

                youtubeVideos.add(youtubeVideo);

                cursor.moveToNext();
            }
        }

        cursor.close();

        return youtubeVideos;
    }

}
