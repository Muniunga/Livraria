package com.example.livraria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity2 extends AppCompatActivity {

    EditText nome, senha;
    Button entrar, criar;
    Bd DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        nome=findViewById(R.id.nome1);
        senha=findViewById(R.id.senha1);
        entrar=findViewById(R.id.entrar1);
        criar=findViewById(R.id.criar);
        DB= new Bd(this);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user=nome.getText().toString();
                String pass=senha.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    Toast.makeText(LoginActivity2.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                else {
                    Boolean verificarsenha=DB.verificarSenha(user,pass);
                    if (verificarsenha==true){
                        Toast.makeText(LoginActivity2.this, "Login bem sucedido", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity2.this, "Login falhado", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });
    }
}