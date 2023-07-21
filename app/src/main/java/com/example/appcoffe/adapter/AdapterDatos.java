package com.example.appcoffe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcoffe.EditCafeActivity;
import com.example.appcoffe.R;
import com.example.appcoffe.data.Cafeteria;

import java.util.ArrayList;

public class AdapterDatos
        extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {
    ArrayList<Cafeteria> listaCafeteria;

    public AdapterDatos(ArrayList<Cafeteria> listaCafeteria) {
        this.listaCafeteria = listaCafeteria;
        listaCafeteria = new ArrayList<>();
        listaCafeteria.addAll(listaCafeteria);
    }

    @NonNull
    @Override
    public AdapterDatos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDatos.ViewHolderDatos holder, int position) {
        holder.nombre.setText(listaCafeteria.get(position).getNombre());
        holder.dueno.setText(listaCafeteria.get(position).getDueno());
        holder.tel.setText(listaCafeteria.get(position).getTelefono());
        holder.dire.setText(listaCafeteria.get(position).getDireccion());
    }

    @Override
    public int getItemCount() {
        return listaCafeteria.size();
    }
    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView nombre, dueno, tel, dire;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txt_local_Nombre);
            dueno = itemView.findViewById(R.id.txt_local_dueno);
            tel = itemView.findViewById(R.id.txt_local_telefono);
            dire = itemView.findViewById(R.id.txt_local_direccion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, EditCafeActivity.class);
                    intent.putExtra("ID", listaCafeteria.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}

