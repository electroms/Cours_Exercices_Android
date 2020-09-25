package com.hb.todo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddTodoActivity extends AppCompatActivity {

    // déclare les variables qui serviront à stocker les objet correspondant à chaque élément
    private Spinner spinner;
    private EditText edtTodo;
    private Button btnAdd;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        // on récupère la bar d'action et on fait afficher le retour
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // récupère les éléments
        spinner = findViewById(R.id.sprUrgency);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        edtTodo = findViewById(R.id.edtTodo);

        // Créé le tableau de valeurs qui seront affichées dans le spinner
        String[] plants = new String[]{
                "Low",
                "Medium",
                "High"
        };

        // converti le tableau en list
        final List<String> plantsList = new ArrayList<>(Arrays.asList(plants));

        // créé un ArrayAdapter qui permet de faire le lien entre les données de la liste et le spinner
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, plantsList);

        // on spécifie la vue qui permettra de mettre un style sur un élément du spinner
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);

        // on plug le spinner avec l'adapter
        spinner.setAdapter(spinnerArrayAdapter);

        // gestion du clic sur btnAdd
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on récupère le context de la'application
                Context context = getApplicationContext();

                // récupère le text saisie par l'utilisateur dans edtTodo
                String nameTodo = edtTodo.getText().toString();
                // récupère le text sélectionné dans le spinner
                String selectedSpinnerItem = spinner.getSelectedItem().toString();

                // créé un intent pour renvoyé le résultat de cette activity à la main activity
                Intent resultIntent = new Intent();
                // ajoute dans l'intent les données récupérées
                resultIntent.putExtra("nameTodo", nameTodo);
                resultIntent.putExtra("urgency", selectedSpinnerItem);
                // indique que cette activity return OK
                setResult(RESULT_OK, resultIntent);

                finish();

            }
        });

        // gestion du clic sur btnCancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // créé un intent pour revenir sur la main activity
                // avec RESULT_CANCELED
                Intent resultIntent = new Intent();
                setResult(RESULT_CANCELED, resultIntent);
                // termine cette activity -> revient sur main activity
                finish();

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        // on ferme cette activity
        finish();

        return true;
    }
}
