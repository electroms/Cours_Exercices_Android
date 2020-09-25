package com.hb.contactproph.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactPro implements Parcelable {

    private long id;
    private String nom;
    private String prenom;
    private String societe;
    private String adresse;
    private String tel;
    private String mail;
    private String website;
    private String secteur;

    public ContactPro() {

    }

    public ContactPro(long id, String nom, String prenom, String societe, String adresse, String tel, String mail, String website, String secteur) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.societe = societe;
        this.adresse = adresse;
        this.tel = tel;
        this.mail = mail;
        this.website = website;
        this.secteur = secteur;
    }

    protected ContactPro(Parcel in) {
        this.id = in.readLong();
        this.nom = in.readString();
        this.prenom = in.readString();
        this.societe = in.readString();
        this.adresse = in.readString();
        this.tel = in.readString();
        this.mail = in.readString();
        this.website = in.readString();
        this.secteur = in.readString();
    }

    public static final Parcelable.Creator<ContactPro> CREATOR = new Parcelable.Creator<ContactPro>() {
        @Override
        public ContactPro createFromParcel(Parcel in) {
            return new ContactPro(in);
        }

        @Override
        public ContactPro[] newArray(int size) {
            return new ContactPro[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(nom);
        parcel.writeString(prenom);
        parcel.writeString(societe);
        parcel.writeString(adresse);
        parcel.writeString(tel);
        parcel.writeString(mail);
        parcel.writeString(website);
        parcel.writeString(secteur);
    }

    @Override
    public String toString() {
        return "ContactPro{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", societe='" + societe + '\'' +
                ", adresse='" + adresse + '\'' +
                ", tel='" + tel + '\'' +
                ", mail='" + mail + '\'' +
                ", website='" + website + '\'' +
                ", secteur='" + secteur + '\'' +
                '}';
    }
}
