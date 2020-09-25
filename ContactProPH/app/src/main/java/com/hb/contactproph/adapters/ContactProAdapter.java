package com.hb.contactproph.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hb.contactproph.R;
import com.hb.contactproph.pojos.ContactPro;

import java.util.List;

public class ContactProAdapter extends RecyclerView.Adapter<ContactProAdapter.ContactProViewHolder> {

    private List<ContactPro> contactPros;

    private OnTitreClickListener onTitreClickListener;

    public ContactProAdapter(List<ContactPro> contactPros) {

        this.contactPros = contactPros;
    }

    public class ContactProViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNom;
        public TextView tvPrenom;
        public TextView tvSociete;
        public TextView tvAdresse;
        public TextView tvTel;
        public TextView tvMail;
        public TextView tvWebsite;

        public ContactProViewHolder(View itemView) {
            super(itemView);

            // on récupère les élements de la vue avec findViewById
            tvNom = itemView.findViewById(R.id.tvNom);
            tvPrenom = itemView.findViewById(R.id.tvPrenom);
            tvSociete = itemView.findViewById(R.id.tvSociete);
            tvAdresse = itemView.findViewById(R.id.tvAdresse);
            tvTel = itemView.findViewById(R.id.tvTel);
            tvMail = itemView.findViewById(R.id.tvMail);
            tvWebsite = itemView.findViewById(R.id.tvWebsite);
        }
    }

    public ContactProAdapter(List<ContactPro> contactPros, OnTitreClickListener onTitreClickListener) {
        this.contactPros = contactPros;

        this.onTitreClickListener = onTitreClickListener;
    }

    @Override
    public ContactProViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_pro_item, parent, false);

        return new ContactProViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactProViewHolder holder, int position) {
        final ContactPro contactPro = contactPros.get(position);

        holder.tvNom.setText(contactPro.getNom());
        holder.tvPrenom.setText(contactPro.getPrenom());
        holder.tvSociete.setText(contactPro.getSociete());
        holder.tvAdresse.setText(contactPro.getAdresse());
        holder.tvTel.setText(contactPro.getTel());
        holder.tvMail.setText(contactPro.getMail());
        holder.tvWebsite.setText(contactPro.getWebsite());

        if (onTitreClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onTitreClickListener.onTitreClick(contactPro);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return contactPros.size();
    }

    // Déclare l'interface qui devra être implémenter pour gérer
    // le click sur le Titre
    // Elle sera constitué d'une méthode onTitreClick qui aura en paramètre
    // un objet youtubeVideo

    public interface OnTitreClickListener {
        void onTitreClick(ContactPro contactPro);
    }
}
