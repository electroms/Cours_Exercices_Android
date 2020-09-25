package com.hb.mybestyoutube;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hb.mybestyoutube.pojos.YoutubeVideo;

public class DetailActivity extends AppCompatActivity {

    private TextView tvTitre;
    private TextView tvDescription;
    private TextView tvUrl;
    private TextView tvCategorie;
    private Button btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Récupère l'objet YoutubeVideo passé dans l'intent
        Intent intent = getIntent();
        YoutubeVideo youtubeVideo = intent.getParcelableExtra("youtubeVideo");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnView = findViewById(R.id.btnView);
        tvTitre = findViewById(R.id.tvTitre);
        tvDescription = findViewById(R.id.tvDescription);
        tvUrl = findViewById(R.id.tvUrl);
        tvCategorie = findViewById(R.id.tvCategorie);

        tvTitre.setText(youtubeVideo.getTitre());
        tvDescription.setText(youtubeVideo.getDescription());
        tvUrl.setText(youtubeVideo.getUrl());
        tvCategorie.setText(youtubeVideo.getCategorie());

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();

                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=5X7WWVTrBvM"));
                try {
                    DetailActivity.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();

        return true;
    }
}
