package com.example.fontexplorer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fontexplorer.API.ApiClient;
import com.example.fontexplorer.API.ServerService;
import com.example.fontexplorer.Entities.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    EditText user, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);

        Button buttonSingUp = findViewById(R.id.botonSign); //Buscamos el botón por su id
        buttonSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);
            }
        });

        //Responde la llamada
        ServerService server = ApiClient.getService();
        Call<List<Usuario>> call = server.getUsuario();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()) {
                    List<Usuario> users = response.body();
                    Button buttonLogin = findViewById(R.id.botonLogin);
                    buttonLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean foundUser = false;
                            for (Usuario u : users) {
                                if (u.getUsuario().equals(user.getText().toString()) && u.getContraseña().equals(pass.getText().toString())) {
                                    Toast.makeText(getApplicationContext(), "Logged", Toast.LENGTH_SHORT).show();
                                    foundUser = true;
                                    break;
                                }
                            }
                            if (!foundUser) {
                                Toast.makeText(getApplicationContext(), "Error login", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    System.out.println("response no es succesful");
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}