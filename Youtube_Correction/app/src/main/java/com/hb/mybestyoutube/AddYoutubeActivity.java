package com.hb.mybestyoutube;

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

import com.hb.mybestyoutube.dao.YoutubeVideoDAO;
import com.hb.mybestyoutube.pojos.YoutubeVideo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddYoutubeActivity extends AppCompatActivity {

    // variable pour les éléments de la vue
    private Button btnAdd;
    private Button btnCancel;
    private EditText etTitre;
    private EditText etDescription;
    private EditText etUrl;
    private Spinner sprCategorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_youtube);

        // ajoute le back dans la barre d'action
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        // récupère les éléments de la vue
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        etTitre = findViewById(R.id.etTitre);
        etDescription = findViewById(R.id.etDescription);
        etUrl = findViewById(R.id.etUrl);
        sprCategorie = findViewById(R.id.sprCategorie);

        // créé la liste qui sera affichée par le spinner
        String[] urgency = new String[]{
                "Sport",
                "Music",
                "Comedy",
                "Cuisine"
        };
        final List<String> urgencyList = new ArrayList<>(Arrays.asList(urgency));

        // créé l'adpater pour le spinner
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_category, urgencyList
        );

        // ajoute l'adapter au spinner
        sprCategorie.setAdapter(spinnerArrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = getApplicationContext();

                String titre = etTitre.getText().toString();
                String description = etDescription.getText().toString();
                String url = etUrl.getText().toString();
                String selectedCategorie = sprCategorie.getSelectedItem().toString();

                if (titre.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Entrez un titre", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (description.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Entrez une description", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (url.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Entrez une url", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {

                    // enregistre dans la bdd avec le DAO
                    YoutubeVideoDAO youtubeVideoDAO = new YoutubeVideoDAO(context);

                    YoutubeVideo youtubeVideo = new YoutubeVideo();
                    youtubeVideo.setTitre(titre);
                    youtubeVideo.setDescription(description);
                    youtubeVideo.setUrl(url);

                    youtubeVideo = youtubeVideoDAO.add(youtubeVideo);

                    // termine l'activity et revient à la main
                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // termine l'activity et revient à la main
                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        // termine cette activity si le back est sélectionné
        finish();
        return true;
    }
}
