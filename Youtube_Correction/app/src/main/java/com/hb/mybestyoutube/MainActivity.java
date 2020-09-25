package com.hb.mybestyoutube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hb.mybestyoutube.adapters.YoutubeVideoAdapter;
import com.hb.mybestyoutube.dao.YoutubeVideoDAO;
import com.hb.mybestyoutube.pojos.YoutubeVideo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String KEY_YOUTUBE = "youtubes";
    private YoutubeVideoDAO youtubeVideoDAO = null;
    private List<YoutubeVideo> youtubeVideos = new ArrayList<>();
    private RecyclerView recyclerView;
    private YoutubeVideoAdapter youtubeVideoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // récupère les éléments
        recyclerView = findViewById(R.id.rvYoutubeVideo);

        // créé et affecte le LayoutManager au recyclerView
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        youtubeVideoDAO = new YoutubeVideoDAO(getApplicationContext());

    }

    // Crée le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Gère le menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // déclare un objet Intent
        Intent intent = null;

        switch (item.getItemId()) {
            case R.id.item_add:
                // appel l'activité AddYoutubeActivity
                intent = new Intent(this, AddYoutubeActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        YoutubeAsyncTasks youtubeAsyncTasks = new YoutubeAsyncTasks();
        youtubeAsyncTasks.execute();
    }

    public class YoutubeAsyncTasks extends AsyncTask<String, String, List<YoutubeVideo>> {

        @Override
        protected List<YoutubeVideo> doInBackground(String... strings) {

            List<YoutubeVideo> youtubeVideos = youtubeVideoDAO.list();

            return youtubeVideos;
        }

        @Override
        protected void onPostExecute(List<YoutubeVideo> responseYoutubeVideo) {
            youtubeVideos = responseYoutubeVideo;
            // créé YoutubeVideoAdapter avec en deuxième paramètre la création d'un objet suivant l'interface OnTitreClickListener
            // délcarée dans YoutubeVideoAdapter
            youtubeVideoAdapter = new YoutubeVideoAdapter(youtubeVideos, new YoutubeVideoAdapter.OnTitreClickListener() {

                // implémente la méthode onTitreClick de l'interface OnTitreClickListener
                @Override
                public void onTitreClick(YoutubeVideo youtubeVideo) {
                    // créée l'objet intent pour démarrer le detailActivity
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra("youtubeVideo", youtubeVideo);
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(youtubeVideoAdapter);
        }
    }
}
