package com.example.fontexplorer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.fontexplorer.API.ApiClient;
import com.example.fontexplorer.API.ServerService;
import com.example.fontexplorer.Entities.EstadistiquesFont;
import com.example.fontexplorer.Entities.Fuente;
import com.example.fontexplorer.Entities.Usuario;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Review extends DialogFragment {
    public Review.OnDismissListener onDismissListener;
    RatingBar calificacion;
    EditText comentario, idFuente;
    List<Fuente> fuentes1;
    Button button;

    public static Review newInstance(String param1, String param2) {
        Review fragment = new Review();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public Review() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        Gson gson = new GsonBuilder().setLenient().create();
        calificacion = view.findViewById(R.id.ratingBar);
        comentario = view.findViewById(R.id.comentarios);
        idFuente = view.findViewById(R.id.idFuente);
        Bundle args = getArguments();
        idFuente.setText(String.valueOf(args.getLong("fuente")));
        button = view.findViewById(R.id.send);

        ServerService server = ApiClient.getService();
        Call<List<Fuente>> call = server.getFuentes();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EstadistiquesFont estadistiquesFont = new EstadistiquesFont();
                estadistiquesFont.setCalificacion(calificacion.getRating());
                estadistiquesFont.setComentarios(comentario.getText().toString());


                String json = gson.toJson(estadistiquesFont);
                String json2 = "{\n" +
                        "  \"calificacion\": " + calificacion.getRating() + ",\n" +
                        "  \"comentarios\": \"" + comentario.getText().toString().replace("\"", "\\\"") + "\",\n" +
                        "  \"fuente\": {\n" +
                        "    \"idFuente\": " + args.getLong("fuente") + "\n" +
                        "  }\n" +
                        "}";
                ServerService serverService = ApiClient.getService();
                Call<EstadistiquesFont> call = serverService.createEstadisticaFont(json2);
                System.out.println(json2);
                call.enqueue(new Callback<EstadistiquesFont>() {
                    @Override
                    public void onResponse(Call<EstadistiquesFont> call, Response<EstadistiquesFont> response) {
                        if (response.isSuccessful()) {
                            EstadistiquesFont nuevoEstadistiquesFont = response.body();
                            System.out.println(nuevoEstadistiquesFont.toString());
                            Toast.makeText(view.getContext(), "Review registrado correctamente", Toast.LENGTH_SHORT).show();
                            System.out.println(nuevoEstadistiquesFont);
                        } else {
                            // Print the response details for debugging
                            System.out.println("Response Code: " + response.code());
                            System.out.println("Response Message: " + response.message());
                            try {
                                System.out.println("Response Body: " + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(view.getContext(), "Error al registrar review", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<EstadistiquesFont> call, Throwable t) {
                        Toast.makeText(view.getContext(), "Review registrado correctamente", Toast.LENGTH_SHORT).show();
                        Log.e("Registro", "Error al registrar review", t);

                        Intent i = new Intent(view.getContext(), Login.class);
                        startActivity(i);
                    }
                });
            }
        });
        return view;
    }
    public void setOnDismissListener(Review.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    public interface OnDismissListener {
        void onDismiss(Review fragment);
    }
    @Override
    public void onStart() {
        super.onStart();
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        getDialog().getWindow().setLayout((int) (width * 1), (int) (height * 1));
    }
}