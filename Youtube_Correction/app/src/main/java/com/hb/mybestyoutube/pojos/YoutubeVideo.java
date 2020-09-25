package com.hb.mybestyoutube.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class YoutubeVideo implements Parcelable {

    private long id;
    private String titre;
    private String description;
    private String url;
    private String categorie;

    public YoutubeVideo() {
    }

    public YoutubeVideo(long id, String titre, String description, String url, String categorie) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.url = url;
        this.categorie = categorie;
    }

    protected YoutubeVideo(Parcel in) {
        this.id = in.readLong();
        this.titre = in.readString();
        this.description = in.readString();
        this.url = in.readString();
        this.categorie = in.readString();
    }

    public static final Creator<YoutubeVideo> CREATOR = new Creator<YoutubeVideo>() {
        @Override
        public YoutubeVideo createFromParcel(Parcel in) {
            return new YoutubeVideo(in);
        }

        @Override
        public YoutubeVideo[] newArray(int size) {
            return new YoutubeVideo[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(titre);
        parcel.writeString(description);
        parcel.writeString(url);
        parcel.writeString(categorie);
    }

    @Override
    public String toString() {
        return "YoutubeVideo{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", categorie='" + categorie + '\'' +
                '}';
    }
}
