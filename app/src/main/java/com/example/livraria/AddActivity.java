package com.example.livraria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText titulo, autor, paginas;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        titulo = findViewById(R.id.titulo);
        autor = findViewById(R.id.autor);
        paginas = findViewById(R.id.paginas);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bd myDB = new Bd(AddActivity.this);
                myDB.addLivro(titulo.getText().toString().trim(),
                        autor.getText().toString().trim(),
                        Integer.valueOf(paginas.getText().toString().trim()));
            }
        });
    }
}