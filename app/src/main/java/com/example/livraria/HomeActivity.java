package com.example.livraria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView img_vazio;
    TextView vazio;

    Bd myDB;
    ArrayList<String> id, titulo, autor, paginas;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        img_vazio = findViewById(R.id.img_vazio);
        vazio = findViewById(R.id.vazio);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(HomeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new Bd(HomeActivity.this);
        id = new ArrayList<>();
        titulo = new ArrayList<>();
        autor = new ArrayList<>();
        paginas = new ArrayList<>();

        armazenamentoDados();

        customAdapter = new CustomAdapter(HomeActivity.this,this, id, titulo, autor,
                paginas);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));

    }
    void armazenamentoDados(){
        Cursor cursor = myDB.lerDados();
        if(cursor.getCount() == 0){
            img_vazio.setVisibility(View.VISIBLE);
            vazio.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                titulo.add(cursor.getString(1));
                autor.add(cursor.getString(2));
                paginas.add(cursor.getString(3));
            }
            img_vazio.setVisibility(View.GONE);
            vazio.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meu_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
             confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Apagar tudo?");
        builder.setMessage("Tens certeza que deseja Apagar tudo?" );
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Bd myDB= new Bd(HomeActivity.this);
                myDB.apagarTudo();

                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();

            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}