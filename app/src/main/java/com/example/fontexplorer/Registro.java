package com.example.fontexplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fontexplorer.API.ApiClient;
import com.example.fontexplorer.API.ServerService;
import com.example.fontexplorer.Entities.Usuario;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registro extends AppCompatActivity {

    EditText nombreEditText, apellidosEditText, emailEditText, usuarioEditText, contraseñaEditText;
    Button btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombreEditText = findViewById(R.id.name_text);
        apellidosEditText = findViewById(R.id.lastname_text);
        emailEditText = findViewById(R.id.mail_text);
        usuarioEditText = findViewById(R.id.username_text);
        contraseñaEditText = findViewById(R.id.password_text);

        btn_registrar = findViewById(R.id.register_btn);
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a Usuario object with the data from the form
                Usuario usuario = new Usuario(
                        String.valueOf(nombreEditText.getText()),
                        String.valueOf(apellidosEditText.getText()),
                        String.valueOf(emailEditText.getText()),
                        String.valueOf(usuarioEditText.getText()),
                        String.valueOf(contraseñaEditText.getText())
                );

                // Send the POST request to the server
                ServerService serverService = ApiClient.getService();
                Call<Void> call = serverService.registerUser(usuario);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error al registrar usuario", Toast.LENGTH_SHORT).show();                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("Registro", "Error al registrar usuario", t);
                        Toast.makeText(getApplicationContext(), "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}