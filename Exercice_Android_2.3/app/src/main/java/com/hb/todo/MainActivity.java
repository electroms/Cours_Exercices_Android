package com.hb.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hb.todo.adapters.TodoAdapter;
import com.hb.todo.dao.TodoDAO;
import com.hb.todo.pojos.Todo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // déclare les éléments
    // private TextView tvTodo;
    public final static String KEY_TODO = "todos";
    private TodoDAO todoDAO = null;
    private List<Todo> todos = new ArrayList<>();
    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // layout du recyclerview
        setContentView(R.layout.recyclerview_main);

        todoDAO = new TodoDAO(getApplicationContext());

        // récupère les éléments
        recyclerView = findViewById(R.id.rvTodo);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        // indique le LayoutManager au RecyclerView
        recyclerView.setLayoutManager(layoutManager);
        // on créé l'adapter
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // créé le menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_todo:

                // appel et démarre l'activity AddTodoActivity avec un intent
                Intent intent = new Intent(getApplicationContext(), AddTodoActivity.class);

                // démarre l'activity
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // Créer l'objet de TodoAsyncTasks
        TodoAsyncTasks todoAsyncTasks = new TodoAsyncTasks();
        // Lance l'éxecution de la tache
        todoAsyncTasks.execute();

        /*
        remplacé par l'utilisation de la tache asyncrone AsyncTask
        // Récupère la liste de todos
        List<Todo> todos = todoDAO.list();

        StringBuilder sb = new StringBuilder();

        // Parcours la liste
        for (Todo todo : todos) {

            sb.append(String.format("%s - %s\n", todo.getName(), todo.getUrgency()));
        }

        // met à jour le textview
        tvTodo.setText(sb.toString());
        */
    }

    public class TodoAsyncTasks extends AsyncTask<String, String, List<Todo>> {

        @Override
        protected List<Todo> doInBackground(String... strings) {

            // Récupère la liste de todos
            List<Todo> todos = todoDAO.list();

            return todos;
        }

        @Override
        protected void onPostExecute(List<Todo> responseTodo) {
            todos = responseTodo;
            // on créé l'adapter
            todoAdapter = new TodoAdapter(todos);
            // indiqure l'adapter au RecyclerView
            recyclerView.setAdapter(todoAdapter);
        }
    }
}
