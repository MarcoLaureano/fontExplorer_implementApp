package com.example.fontexplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fontexplorer.API.ServerService;
import com.example.fontexplorer.Entities.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registro extends AppCompatActivity {

    EditText nombreEditText, apellidosEditText, emailEditText, usuarioEditText, contraseñaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombreEditText = findViewById(R.id.name_text);
        apellidosEditText = findViewById(R.id.lastname_text);
        emailEditText = findViewById(R.id.mail_text);
        usuarioEditText = findViewById(R.id.username_text);
        contraseñaEditText = findViewById(R.id.password_text);

        // Crea un objeto Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tu_servidor.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crea un objeto UserService a partir de la interfaz
        ServerService userService = retrofit.create(ServerService.class);

        // Crea un objeto Usuario con los datos del formulario
        Usuario usuario = new Usuario();
        usuario.setNombre(nombreEditText.getText().toString());
        usuario.setApellidos(apellidosEditText.getText().toString());
        usuario.setEmail(emailEditText.getText().toString());
        usuario.setUsuario(usuarioEditText.getText().toString());
        usuario.setContraseña(contraseñaEditText.getText().toString());

        // Hace la solicitud POST al servidor
        Call<Usuario> call = userService.createUsuario(usuario);
        call.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                // Aquí puedes manejar la respuesta del servidor
                if (response.isSuccessful()) {
                    Usuario nuevoUsuario = response.body();
                    Toast.makeText(getApplicationContext(), "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                    // Aquí puedes hacer algo con el objeto nuevoUsuario que recibiste del servidor
                } else {
                    Toast.makeText(getApplicationContext(), "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                // Aquí puedes manejar errores de conexión u otros errores
                Toast.makeText(getApplicationContext(), "Error al registrar usuario", Toast.LENGTH_SHORT).show();
            }
        });

    }
}