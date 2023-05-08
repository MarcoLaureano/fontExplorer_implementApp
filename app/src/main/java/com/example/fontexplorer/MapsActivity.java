package com.example.fontexplorer;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fontexplorer.API.ApiClient;
import com.example.fontexplorer.API.ServerService;
import com.example.fontexplorer.Entities.Fuente;
import com.example.fontexplorer.Entities.Usuario;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.fontexplorer.databinding.ActivityMapsBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Button fuentes, fuenteCercana, salir;
    private LatLng initialLocation = new LatLng(41.3826198, 2.1489915);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    99);
        } else {

        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ServerService server = ApiClient.getService();
        Call<List<Fuente>> call = server.getFuentes();

        fuentes=findViewById(R.id.Fuentes);
        fuentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng newLocation = new LatLng(41.3826198,2.1489915);
                zoomToCurrentLocation(newLocation,12.5f);

                call.enqueue(new Callback<List<Fuente>>() {
                    @Override
                    public void onResponse(Call<List<Fuente>> call, Response<List<Fuente>> response) {
                        if(response.isSuccessful()) {
                            List<Fuente> fuentes = response.body();
                                for (Fuente f : fuentes) {
                                    LatLng latLng = new LatLng(f.getLatitud(), f.getLongitud());
                                    mMap.addMarker(new MarkerOptions().position(latLng).title(f.getNombre()).snippet(f.getDescripcion()));
                                }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Fuente>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
            }
        });


        fuenteCercana = findViewById(R.id.FuentesCercana);
        fuenteCercana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng newLocation = new LatLng(41.3826198,2.1489915);
                zoomToCurrentLocation(newLocation,15.5f);
            }
        });


        salir = (Button) findViewById(R.id.botonSalir);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 15));
    }

    private void zoomToCurrentLocation(LatLng location, float zoomLevel) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, zoomLevel);
        mMap.animateCamera(cameraUpdate);
    }
}