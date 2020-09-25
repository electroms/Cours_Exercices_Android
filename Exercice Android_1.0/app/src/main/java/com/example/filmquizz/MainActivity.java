package com.example.filmquizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filmquizz.business.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FilmQuizz";
    private static final String KEY_INDEX = "index";
    private static final String KEY_SCORE = "score";
    public static final String KEY_QUESTION = "question";

    // Variable pour récupérer les éléments de la vue
    private Button btnTrue;
    private Button btnFalse;
    private TextView tvQuestion;
    private TextView tvScore;


    private List<Question> questions = new ArrayList<Question>();
    private int questionEnCours = 1;
    private int score = 0;
    // objet pour la question en cours
    private Question question;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate() called");

        // lier l'activity avec la vue
        setContentView(R.layout.activity_main);

        /*TextView tvHello = findViewById(R.id.tvHello);
        tvHello.setText("Je modifie le text avec du java");
        */

        // On récupère les éléments de la vue
        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);

        // Ajoute dans la liste les questions
        questions.add(new Question(1, getString(R.string.question_2001), false));
        questions.add(new Question(2, getString(R.string.question_ai), true));
        questions.add(new Question(3, getString(R.string.question_citizen_kane), false));
        questions.add(new Question(4, getString(R.string.question_reservoir_dogs), true));
        questions.add(new Question(5, getString(R.string.question_taxi_driver), true));

        // Récupères les données enregistrées dans savedInstancesState lors
        // de l'appel à la méthode onSavedInstancesState

        if (savedInstanceState != null) {
            questionEnCours = savedInstanceState.getInt(KEY_INDEX);
            score = savedInstanceState.getInt(KEY_SCORE);
        }

        // récupère la question en cours pour la première fois
        question = questions.get(questionEnCours - 1);
        // Affiche la question
        tvQuestion.setText(question.getText());

        // met à jour le score pour la première fois
        tvScore.setText("Score : " + score);
        // ou avec String.format
        tvScore.setText(String.format("Score : %d points", score));

        // Gestion du clic sur le bouton true
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // on récupère le context de la'application
                Context context = getApplicationContext();

                if (question.isAnswer() == true && questionEnCours < questions.size()) {
                    // créé un toast et l'affiche
                    Toast toast = Toast.makeText(context, "Juste", Toast.LENGTH_SHORT);
                    toast.show();

                    // augmente le score
                    score++;
                    // met à jour le texte du score
                    tvScore.setText(String.format("Score : %d points", score));
                }

                // on passe à la question suivante
                questionEnCours++;

                // on test si on a dépassé le nombre de question dans la liste
                if (questionEnCours <= questions.size()) {
                    question = questions.get(questionEnCours - 1);
                    tvQuestion.setText(question.getText());
                } else {
                    Toast toast = Toast.makeText(context, "Fin de la partie", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        // Gestion du clic sur le bouton false
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // on récupère le context de la'application
                Context context = getApplicationContext();

                if (question.isAnswer() == false && questionEnCours < questions.size()) {
                    // créé un toast et l'affiche
                    Toast toast = Toast.makeText(context, "Juste", Toast.LENGTH_SHORT);
                    toast.show();

                    // augmente le score
                    score++;
                    // met à jour le texte du score
                    tvScore.setText(String.format("Score : %d points", score));
                }

                // on passe à la question suivante
                questionEnCours++;

                // on test si on a dépassé le nombre de question dans la liste
                if (questionEnCours <= questions.size()) {
                    question = questions.get(questionEnCours - 1);
                    tvQuestion.setText(question.getText());
                } else {
                    Toast toast = Toast.makeText(context, "Fin de la partie", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cheat:
                // On créé un Bundle pour stocker l'objet question
                Bundle bundle = new Bundle();
                bundle.putSerializable(KEY_QUESTION, question);

                // Appel l'activity CheatActivity avec un intent
                Intent intent = new Intent(getApplicationContext(), CheatActivity.class);
                intent.putExtras(bundle);
                // démarre l'activity qui est renseigné dans le bundle
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState() called");

        outState.putInt(KEY_INDEX, questionEnCours);
        outState.putInt(KEY_SCORE, score);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}