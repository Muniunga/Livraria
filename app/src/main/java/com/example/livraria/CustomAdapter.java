package com.example.livraria;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList id, titulo, autor, paginas;

    Animation translate_anim;

    CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList titulo, ArrayList autor,
                  ArrayList paginas){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.titulo_txt.setText(String.valueOf(titulo.get(position)));
        holder.autor_txt.setText(String.valueOf(autor.get(position)));
        holder.paginas_txt.setText(String.valueOf(paginas.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AtualizarActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("titulo", String.valueOf(titulo.get(position)));
                intent.putExtra("autor", String.valueOf(autor.get(position)));
                intent.putExtra("paginas", String.valueOf(paginas.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, titulo_txt, autor_txt, paginas_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            titulo_txt = itemView.findViewById(R.id.titulo_txt);
            autor_txt = itemView.findViewById(R.id.autor_txt);
            paginas_txt = itemView.findViewById(R.id.paginas_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
