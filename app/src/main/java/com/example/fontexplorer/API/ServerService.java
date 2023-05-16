package com.example.fontexplorer.API;


import com.example.fontexplorer.Entities.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServerService {
    @GET("/usuarios/seeUsuarios")
    Call<List<Usuario>> getUsuario();

    @POST("/usuarios/registerUser")
    Call<Void> registerUser(@Body Usuario usuario);
}
