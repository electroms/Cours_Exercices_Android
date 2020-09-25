package com.hb.todo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.hb.todo.R;
import com.hb.todo.pojos.Todo;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<Todo> todos;

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvUrgency;

        public TodoViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvUrgency = itemView.findViewById(R.id.tvUrgency);
        }

    }

    public TodoAdapter(List<Todo> todos) {
        this.todos = todos;
    }

    @Override
    public TodoViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        // Créé un objet view par rapport à un layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);

        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder( TodoViewHolder holder, int position) {
        Todo todo = todos.get(position);

        // lie les données d'un Todo avec les éléments contenu dans la vue
        holder.tvName.setText(todo.getName());
        holder.tvUrgency.setText(todo.getUrgency());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }
}
