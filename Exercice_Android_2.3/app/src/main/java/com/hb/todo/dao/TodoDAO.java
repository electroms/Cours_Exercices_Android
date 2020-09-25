package com.hb.todo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.hb.todo.db.TodoDBHelper;
import com.hb.todo.pojos.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoDAO extends DAO {

    // constructeur qui créé l'objet TodoDBHelper
    public TodoDAO(Context context) {
        super(new TodoDBHelper(context));
    }

    public Todo find(Long id) {
        Todo todo = null;

        // ouvre la bdd
        open();

        // exécute la requette select * from Todo where id = ?
        // on récupère le résultat dans un objet Cursor
        Cursor cursor = db.rawQuery("select * from " + TodoDBHelper.TODO_TABLE_NAME +
                " where " + TodoDBHelper.TODO_KEY + " = ?",
                new String[] { String.valueOf(id) });

        // test si on a bien un objet cursor
        // positionne sur le premier enregistrement
        if (cursor != null && cursor.moveToFirst()) {
            // créé l'objet Todo
            todo = new Todo();
            // renseigne les valeurs des attributs
            todo.setId(cursor.getLong(TodoDBHelper.TODO_KEY_COLUMN_INDEX));
            todo.setName(cursor.getString(TodoDBHelper.TODO_NAME_COLUMN_INDEX));
            todo.setUrgency(cursor.getString(TodoDBHelper.TODO_URGENCY_COLUMN_INDEX));

            cursor.close();
        }

        // ferme la bdd
        close();

        return todo;
    }

    public List<Todo> list() {

        // créé la liste qui sera retoruné
        List<Todo> todos = new ArrayList<>();

        // ouvre la bdd
        open();

        // exécute la requette select * from Todo
        // on récupère le résultat dans un objet Cursor
        Cursor cursor = db.rawQuery("select * from " + TodoDBHelper.TODO_TABLE_NAME,
                null);

        // test si on a bien un objet cursor
        // positionne sur le premier enregistrement
        if (cursor != null && cursor.moveToFirst()) {
            // boucle tant que on est pas après le dernier enregistrement
            while (!cursor.isAfterLast()) {

                // créé l'objet Todo
                Todo todo = new Todo();
                // renseigne les valeurs des attributs
                todo.setId(cursor.getLong(TodoDBHelper.TODO_KEY_COLUMN_INDEX));
                todo.setName(cursor.getString(TodoDBHelper.TODO_NAME_COLUMN_INDEX));
                todo.setUrgency(cursor.getString(TodoDBHelper.TODO_URGENCY_COLUMN_INDEX));

                // ajoute l'objet à la liste
                todos.add(todo);

                // passe à l'enregistrement suivant
                cursor.moveToNext();
            }

            cursor.close();
        }

        close();

        // retourne la liste
        return todos;
    }

    public Todo add(Todo todo) {
        open();

        // créé un objet ContentValues qui stock les valeus à insérées
        ContentValues values = new ContentValues();

        // ajoutes les valeurs à insérées
        values.put(TodoDBHelper.TODO_NAME, todo.getName());
        values.put(TodoDBHelper.TODO_URGENCY, todo.getUrgency());

        // exécute l'insert sur la table et récupère l'id généré
        long id = db.insert(TodoDBHelper.TODO_TABLE_NAME, null, values);

        // met à jour l'attribut id
        todo.setId(id);

        close();

        return todo;
    }

    public void delete(Todo todo) {
        open();

        // exécute le delete avec la clause where id = ?
        db.delete(TodoDBHelper.TODO_TABLE_NAME, TodoDBHelper.TODO_KEY + " = ?",
                new String[] { String.valueOf(todo.getId()) });

        close();
    }

    public void update(Todo todo) {
        open();

        // créé un objet ContentValues qui stock les valeus à insérées
        ContentValues values = new ContentValues();

        // ajoutes les valeurs à mettre à jours
        values.put(TodoDBHelper.TODO_NAME, todo.getName());
        values.put(TodoDBHelper.TODO_URGENCY, todo.getUrgency());

        // exécute l'update à les valeurs à mettre à jour
        // et la clause id = ?
        db.update(TodoDBHelper.TODO_TABLE_NAME, values,
                TodoDBHelper.TODO_KEY + " = ?",
                new String[] { String.valueOf(todo.getId()) });

        close();
    }
}
