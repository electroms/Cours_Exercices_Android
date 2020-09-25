package com.hb.contactproph.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.hb.contactproph.db.ContactProDbHelper;
import com.hb.contactproph.pojos.ContactPro;

import java.util.ArrayList;
import java.util.List;

public class ContactProDAO extends DAO {

    public ContactProDAO(Context context) {
        super(new ContactProDbHelper(context));
    }

    public ContactPro find(Long id) {
        ContactPro contactPro = null;

        Cursor cursor = db.rawQuery("select * from " + ContactProDbHelper.CONTACTPRO_TABLE_NAME +
                        " where " + ContactProDbHelper.CONTACTPRO_KEY + " = ?",
                new String[]{String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()) {
            contactPro = new ContactPro();

            contactPro.setId(Long.parseLong(cursor.getString(ContactProDbHelper.CONTACTPRO_KEY_COLUMN_INDEX)));
            contactPro.setNom(cursor.getString(ContactProDbHelper.CONTACTPRO_NOM_COLUMN_INDEX));
            contactPro.setPrenom(cursor.getString(ContactProDbHelper.CONTACTPRO_PRENOM_COLUMN_INDEX));
            contactPro.setSociete(cursor.getString(ContactProDbHelper.CONTACTPRO_SOCIETE_COLUMN_INDEX));
            contactPro.setAdresse(cursor.getString(ContactProDbHelper.CONTACTPRO_ADRESSE_COLUMN_INDEX));
            contactPro.setTel(cursor.getString(ContactProDbHelper.CONTACTPRO_TEL_COLUMN_INDEX));
            contactPro.setMail(cursor.getString(ContactProDbHelper.CONTACTPRO_MAIL_COLUMN_INDEX));
            contactPro.setWebsite(cursor.getString(ContactProDbHelper.CONTACTPRO_WEBSITE_COLUMN_INDEX));
            contactPro.setSecteur(cursor.getString(ContactProDbHelper.CONTACTPRO_SECTEUR_COLUMN_INDEX));

            cursor.close();
        }

        return contactPro;
    }

    public ContactPro add(ContactPro contactPro) {

        open();

        ContentValues value = new ContentValues();

        value.put(ContactProDbHelper.CONTACTPRO_NOM, contactPro.getNom());
        value.put(ContactProDbHelper.CONTACTPRO_PRENOM, contactPro.getPrenom());
        value.put(ContactProDbHelper.CONTACTPRO_SOCIETE, contactPro.getSociete());
        value.put(ContactProDbHelper.CONTACTPRO_ADRESSE, contactPro.getAdresse());
        value.put(ContactProDbHelper.CONTACTPRO_TEL, contactPro.getTel());
        value.put(ContactProDbHelper.CONTACTPRO_MAIL, contactPro.getMail());
        value.put(ContactProDbHelper.CONTACTPRO_WEBSITE, contactPro.getWebsite());
        value.put(ContactProDbHelper.CONTACTPRO_SECTEUR, contactPro.getSecteur());

        long id = db.insert(ContactProDbHelper.CONTACTPRO_TABLE_NAME, null, value);

        contactPro.setId(id);

        return contactPro;
    }

    public List<ContactPro> list() {
        open();

        List<ContactPro> contactPros = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " +
                ContactProDbHelper.CONTACTPRO_TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                ContactPro contactPro = new ContactPro();

                contactPro.setId(Long.parseLong(cursor.getString(ContactProDbHelper.CONTACTPRO_KEY_COLUMN_INDEX)));
                contactPro.setNom(cursor.getString(ContactProDbHelper.CONTACTPRO_NOM_COLUMN_INDEX));
                contactPro.setPrenom(cursor.getString(ContactProDbHelper.CONTACTPRO_PRENOM_COLUMN_INDEX));
                contactPro.setSociete(cursor.getString(ContactProDbHelper.CONTACTPRO_SOCIETE_COLUMN_INDEX));
                contactPro.setAdresse(cursor.getString(ContactProDbHelper.CONTACTPRO_ADRESSE_COLUMN_INDEX));
                contactPro.setTel(cursor.getString(ContactProDbHelper.CONTACTPRO_TEL_COLUMN_INDEX));
                contactPro.setMail(cursor.getString(ContactProDbHelper.CONTACTPRO_MAIL_COLUMN_INDEX));
                contactPro.setWebsite(cursor.getString(ContactProDbHelper.CONTACTPRO_WEBSITE_COLUMN_INDEX));
                contactPro.setSecteur(cursor.getString(ContactProDbHelper.CONTACTPRO_SECTEUR_COLUMN_INDEX));

                contactPros.add(contactPro);

                cursor.moveToNext();
            }
        }

        cursor.close();

        return contactPros;
    }
}
