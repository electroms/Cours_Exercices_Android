package com.hb.contactproph;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hb.contactproph.pojos.ContactPro;

public class DetailActivity extends AppCompatActivity {

    private TextView tvNom;
    private TextView tvPrenom;
    private TextView tvSociete;
    private TextView tvAdresse;
    private TextView tvTel;
    private TextView tvMail;
    private TextView tvWebsite;
    private TextView tvSecteur;
    private Button btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        ContactPro contactPro = intent.getParcelableExtra("contactPro");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnView = findViewById(R.id.btnView);
        tvNom = findViewById(R.id.tvNom);
        tvPrenom = findViewById(R.id.tvPrenom);
        tvSociete = findViewById(R.id.tvSociete);
        tvAdresse = findViewById(R.id.tvAdresse);
        tvTel = findViewById(R.id.tvTel);
        tvMail = findViewById(R.id.tvMail);
        tvWebsite = findViewById(R.id.tvWebsite);
        tvSecteur = findViewById(R.id.tvSecteur);

        tvNom.setText(contactPro.getNom());
        tvPrenom.setText(contactPro.getPrenom());
        tvSociete.setText(contactPro.getSociete());
        tvAdresse.setText(contactPro.getAdresse());
        tvTel.setText(contactPro.getTel());
        tvMail.setText(contactPro.getMail());
        tvWebsite.setText(contactPro.getWebsite());
        tvSecteur.setText(contactPro.getSecteur());

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tvWebsite.getText().toString()));

                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();

        return true;
    }
}