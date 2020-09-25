package com.hb.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // déclare les éléments
    private TextView tvTodo;
    public final static String KEY_TODO = "todos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // récupère les éléments
        tvTodo = findViewById(R.id.tvTodo);

        // initialise le texte de tvTodo
        tvTodo.setText("");

        // le bundle n'est pas vide (cas ou on a exécuté onSaveInstanceState)
        if (savedInstanceState != null) {
            tvTodo.setText(savedInstanceState.getString(KEY_TODO));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // créé le menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add_todo:

                // appel et démarre l'activity AddTodoActivity avec un intent
                Intent intent = new Intent(getApplicationContext(), AddTodoActivity.class);

                // démarre l'activity en indiquant que l'on va récupérer un résultat
                startActivityForResult(intent, 10);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // test si le requestCode est le meme que celui utilisé lors de l'appel à startActivityForResult
        if (requestCode == 10) {
            // test si c'est RESULT_OK qui est renvoyé par AddTodoActivity
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    // récupère les données qui ont étées mises dans l'intent
                    String nameTodo = data.getStringExtra("nameTodo");
                    String urgency = data.getStringExtra("urgency");

                    StringBuilder sb = new StringBuilder(tvTodo.getText());

                    sb.append(String.format("%s - %s\n", nameTodo, urgency));

                    tvTodo.setText(sb.toString());
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        // récupère le contenu de tvTodo
        String todos = tvTodo.getText().toString();

        // enregistre le contenu dans le bundle
        outState.putString(KEY_TODO, todos);
    }
}
