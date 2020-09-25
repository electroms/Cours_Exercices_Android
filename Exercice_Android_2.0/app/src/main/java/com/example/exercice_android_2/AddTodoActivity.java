package com.example.exercice_android_2;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddTodoActivity extends AppCompatActivity {

    private Button btnAdd;
    private Button btnCancel;
    private EditText editText;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        editText = findViewById(R.id.edtTodo);


        // récupère les éléments
        spinner = findViewById(R.id.sprUrgency);

        // Créer le tableau de valeurs qui seront affichées dans le spinner
        String[] plants = new String[]{
                "Low",
                "Medium",
                "High"
        };

        // Converti le tableau en liste
        final List<String> plantsList = new ArrayList<>(Arrays.asList(plants));

        // Créer un ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, plantsList);

        // On spécifie la vue qui affiche un élément du spinner
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);

        // On plug le spinner avec l'adapter
        spinner.setAdapter(spinnerArrayAdapter);

        // Gestion du clic sur le bouton add
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String nameTodo = edtTodo.getText().toString();

                String selectedSpinnerItem = spinner.getSelectedItem().toString();

                Intent resultIntent = new Intent();


                // on récupère le context de la'application
                Context context = getApplicationContext();
            }
        });

        // Gestion du clic sur le bouton cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // on récupère le context de la'application
                Context context = getApplicationContext();
            }
        });
    }

        @Override
        public boolean onSupportNavigateUp () {
            finish();

            return true;
        }
}