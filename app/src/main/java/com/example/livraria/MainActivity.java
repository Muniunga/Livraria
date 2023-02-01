package com.example.livraria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.livraria.LoginActivity2;
import com.example.livraria.R;

public class MainActivity extends AppCompatActivity {
    EditText nome,senha,repsenha;
    Button cadastrar,iniciar;
    Bd DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome=findViewById(R.id.nome);
        senha=findViewById(R.id.senha);
        repsenha=findViewById(R.id.repsenha);
        cadastrar=findViewById(R.id.cadastrar);
        iniciar=findViewById(R.id.iniciar);
        DB= new Bd(this);

        cadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String usuario=nome.getText().toString();
                String pass=senha.getText().toString();
                String repass=repsenha.getText().toString();

                if (TextUtils.isEmpty(usuario) ||  TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(MainActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                else{
                    if (pass.equals(repass)){
                        Boolean verificarUsuario= DB.verficarUsuario(usuario);
                        if (verificarUsuario==false){
                            Boolean inserir= DB.inserirDados(usuario,pass);
                            if (inserir==true){
                                Toast.makeText(MainActivity.this, "Registado com sucesso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Registo Falhou", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "Nome de usuario já existe", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Senhas diferentes", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity2.class);
                startActivity(intent);

            }
        });
    }
}