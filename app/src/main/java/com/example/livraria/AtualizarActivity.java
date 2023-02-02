package com.example.livraria;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class AtualizarActivity extends AppCompatActivity {


    EditText titulo, autor, paginas;
    Button update_button, delete_button;

  String id, titulos, autores, pagina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar);

        titulo = findViewById(R.id.titulo2);
        autor = findViewById(R.id.autor2);
        paginas = findViewById(R.id.paginas2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //
        getAndSetIntentData();

        //
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(titulos);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                Bd myDB = new Bd(AtualizarActivity.this);
                titulos = titulo.getText().toString().trim();
                autores = autor.getText().toString().trim();
                pagina = paginas.getText().toString().trim();
                myDB.atualizar(id, titulos, autores, pagina);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("titulo") &&
                getIntent().hasExtra("autor") && getIntent().hasExtra("paginas")){
            //Pegar dados da intent
            id = getIntent().getStringExtra("id");
            titulos = getIntent().getStringExtra("titulo");
            autores = getIntent().getStringExtra("autor");
            pagina = getIntent().getStringExtra("paginas");

            //
            titulo.setText(titulos);
            autor.setText(autores);
            paginas.setText(pagina);
            Log.d("stev", titulo+" "+autor+" "+paginas);
        }else{
            Toast.makeText(this, "Sem dados.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Apagar " + titulo + " ?");
        builder.setMessage("Tens certeza que deseja Apagar " + titulo + " ?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Bd myDB = new Bd(AtualizarActivity.this);
                myDB.apagar(id);
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
