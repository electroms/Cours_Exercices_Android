package com.hb.filmquizz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.hb.filmquizz.business.Question;

public class CheatActivity extends AppCompatActivity {

    private TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        // on récupère la bar d'action et on fait afficher le retour
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // récupère les éléments
        tvResponse = findViewById(R.id.tvResponse);

        // récupère l'intent qui a été utilisé pour appeler cette activity
        Intent intent = getIntent();
        // récupère le bundle
        Bundle bundle = intent.getExtras();
        // récupère l'objet question qui est dans le nundle
        Question question = (Question)bundle.getSerializable(MainActivity.KEY_QUESTION);

        tvResponse.setText(String.format("%s : %s", question.getText(), question.isAnswer()));
    }

    @Override
    public boolean onSupportNavigateUp() {

        // on ferme cette activity
        finish();

        return true;
    }
}
