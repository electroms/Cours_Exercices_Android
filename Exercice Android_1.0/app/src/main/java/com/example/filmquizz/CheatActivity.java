package com.example.filmquizz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.filmquizz.business.Question;

public class CheatActivity extends AppCompatActivity {

    private TextView tvReponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        // on récupère la bar d'action et on fait afficher le retour
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // récupère les éléments
        tvReponse = findViewById(R.id.tvReponse);

        // récupère l'intent qui a été utilisé pour appeler cette Activity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        // Récupère l'objet question qui est dans le Bundle
        Question question = (Question)bundle.getSerializable(MainActivity.KEY_QUESTION);

        tvReponse.setText(String.format("%s %s", question.getText(), question.isAnswer()));
    }

    @Override
    public boolean onSupportNavigateUp() {

        // on ferme cette activity
        finish();
        return true;
    }
}