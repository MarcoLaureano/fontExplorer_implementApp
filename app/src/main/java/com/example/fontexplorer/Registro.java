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
                // Crea un objeto Usuario con los datos del formulario
                Usuario usuario = new Usuario();
                usuario.setNombre(nombreEditText.getText().toString());
                usuario.setApellidos(apellidosEditText.getText().toString());
                usuario.setEmail(emailEditText.getText().toString());
                usuario.setUsuario(usuarioEditText.getText().toString());
                usuario.setContraseña(contraseñaEditText.getText().toString());

                // Envía la solicitud POST al servidor
                ServerService serverService = ApiClient.getService();
                Call<Usuario> call = serverService.createUsuario(usuario);
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if (response.isSuccessful()) {
                            Usuario nuevoUsuario = response.body();
                            Toast.makeText(getApplicationContext(), "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                            System.out.println(nuevoUsuario);
                        } else {
                            Toast.makeText(getApplicationContext(), "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                        Log.e("Registro", "Error al registrar usuario", t);
                    }
                });
            }
        });
    }
}