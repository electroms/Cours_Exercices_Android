package com.hb.contactproph;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hb.contactproph.dao.ContactProDAO;
import com.hb.contactproph.pojos.ContactPro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddContactProActivity extends AppCompatActivity {

    private Button btnAdd;
    private Button btnCancel;
    private EditText etNom;
    private EditText etPrenom;
    private EditText etSociete;
    private EditText etAdresse;
    private EditText etTel;
    private EditText etMail;
    private EditText etWebsite;
    private Spinner sprSecteur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_pro);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        etNom = findViewById(R.id.etNom);
        etPrenom = findViewById(R.id.etPrenom);
        etSociete = findViewById(R.id.etSociete);
        etAdresse = findViewById(R.id.etAdresse);
        etTel = findViewById(R.id.etTel);
        etMail = findViewById(R.id.etMail);
        etWebsite = findViewById(R.id.etWebsite);
        sprSecteur = findViewById(R.id.sprSecteur);

        String[] urgency = new String[]{
                "Industrie",
                "Informatique",
                "Santé",
                "Grande distribution"
        };
        final List<String> urgencyList = new ArrayList<>(Arrays.asList(urgency));

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_secteur, urgencyList
        );

        sprSecteur.setAdapter(spinnerArrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = getApplicationContext();

                String nom = etNom.getText().toString();
                String prenom = etPrenom.getText().toString();
                String societe = etSociete.getText().toString();
                String adresse = etAdresse.getText().toString();
                String tel = etTel.getText().toString();
                String mail = etMail.getText().toString();
                String website = etWebsite.getText().toString();

                String selectedSecteur = sprSecteur.getSelectedItem().toString();

                if (nom.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Entrez un nom", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (prenom.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Entrez une prenom", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (societe.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Entrez une société", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (adresse.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Entrez une adresse", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (tel.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Entrez un numéro de téléphone", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (mail.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Entrez un email", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (website.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Entrez une url", Toast.LENGTH_SHORT);
                    toast.show();
                } else {

                    ContactPro contactPro = new ContactPro();
                    contactPro.setNom(nom);
                    contactPro.setPrenom(prenom);
                    contactPro.setSociete(societe);
                    contactPro.setAdresse(adresse);
                    contactPro.setTel(tel);
                    contactPro.setMail(mail);
                    contactPro.setWebsite(website);
                    contactPro.setSecteur(selectedSecteur);

                    ContactProDAO contactProDAO = new ContactProDAO(getApplicationContext());
                    contactPro = contactProDAO.add(contactPro);

                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}