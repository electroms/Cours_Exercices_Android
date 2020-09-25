package com.hb.contactproph;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hb.contactproph.adapters.ContactProAdapter;
import com.hb.contactproph.dao.ContactProDAO;
import com.hb.contactproph.pojos.ContactPro;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String KEY_CONTACTPRO = "contactPros";
    private ContactProDAO contactProDAO = null;
    private List<ContactPro> contactPros = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContactProAdapter contactProAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvContactPro);

        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        contactProDAO = new ContactProDAO(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;

        switch (item.getItemId()) {
            case R.id.item_add:
                intent = new Intent(this, AddContactProActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        ContactProAsyncTasks contactProAsyncTasks = new ContactProAsyncTasks();
        contactProAsyncTasks.execute();
    }

    public class ContactProAsyncTasks extends AsyncTask<String, String, List<ContactPro>> {

        @Override
        protected List<ContactPro> doInBackground(String... strings) {

            List<ContactPro> contactPros = contactProDAO.list();

            return contactPros;
        }


        @Override
        protected void onPostExecute(List<ContactPro> updateContactPros) {

            updateContactProAdapter(updateContactPros);
        }
    }

    private void updateContactProAdapter(List<ContactPro> updateContactPros) {

        contactPros = updateContactPros;
        contactProAdapter = new ContactProAdapter(contactPros, new ContactProAdapter.OnTitreClickListener() {

            @Override
            public void onTitreClick(ContactPro contactPro) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("contactPro", contactPro);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(contactProAdapter);
    }
}