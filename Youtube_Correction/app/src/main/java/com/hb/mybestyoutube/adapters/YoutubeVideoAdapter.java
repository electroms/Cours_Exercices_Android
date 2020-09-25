package com.hb.mybestyoutube.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hb.mybestyoutube.R;
import com.hb.mybestyoutube.pojos.YoutubeVideo;

import java.util.List;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeVideoViewHolder> {

    // stock la liste youtubeVideos
    private List<YoutubeVideo> youtubeVideos;
    // stock ClickListener qui sera passé en paramètre pour traiter
    // le click sur le titre
    private OnTitreClickListener onTitreClickListener;

    public YoutubeVideoAdapter(List<YoutubeVideo> youtubeVideos) {

        this.youtubeVideos = youtubeVideos;
    }

    // classe qui va gérer l'affichage d'un item
    public class YoutubeVideoViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitre;
        public TextView tvDescription;
        public TextView tvUrl;

        public YoutubeVideoViewHolder(View itemView) {
            super(itemView);

            // on récupère les élements de la vue avec findViewById
            tvTitre = itemView.findViewById(R.id.tvTitre);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvUrl = itemView.findViewById(R.id.tvUrl);
        }
    }

    public YoutubeVideoAdapter(List<YoutubeVideo> youtubeVideos, OnTitreClickListener onTitreClickListener) {
        // on enregistre la liste dans notre attribut youtubeVideos
        this.youtubeVideos = youtubeVideos;

        // on enregistre le ClickListener dans notre attribut onTitreClickListener
        this.onTitreClickListener = onTitreClickListener;
    }

    // Créé la view pour l’item
    @Override
    public YoutubeVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.youtube_video_item, parent, false);

        // retourne notre objet YoutubeVideoViewHolder qui gère un item
        return new YoutubeVideoViewHolder(view);
    }

    // bindding : on crée une laison entre les données qui ce trouve dans un YoutubeVideo (titre, description)
    // avec l'affichage dans un item (avec les TextView tvTitre, tvDescription)
    @Override
    public void onBindViewHolder(@NonNull YoutubeVideoViewHolder holder, int position) {
        // récupérer le youtubeVideo en cours par rapport à la position
        final YoutubeVideo youtubeVideo = youtubeVideos.get(position);

        // on bind
        // à droite on la vue / à gauche on à la donnée
        holder.tvTitre.setText(youtubeVideo.getTitre());
        holder.tvDescription.setText(youtubeVideo.getDescription());
        holder.tvUrl.setText(youtubeVideo.getUrl());

        // ajoute le ClickListener sur Titre
        if (onTitreClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onTitreClickListener.onTitreClick(youtubeVideo);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return youtubeVideos.size();
    }

    // Déclare l'interface qui devra être implémenter pour gérer
    // le click sur le Titre
    // Elle sera constitué d'une méthode onTitreClick qui aura en paramètre
    // un objet youtubeVideo

    public interface OnTitreClickListener {
        void onTitreClick(YoutubeVideo youtubeVideo);
    }
}
