package com.example.fontexplorer.Entities;

import java.util.ArrayList;
import java.util.List;

public class Fuente {
    private Long idFuente;

    private String nombre;

    private Double latitud;

    private Double longitud;

    private String descripcion;

    private List<EstadistiquesFont> estadistiquesFont= new ArrayList<>();

    public Fuente() {}

    public Fuente(String nombre, Double latitud, Double longitud, String descripcion, List<EstadistiquesFont> estadistiquesFont) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcion = descripcion;
        this.estadistiquesFont = estadistiquesFont;
    }

    public Long getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(Long idFuente) {
        this.idFuente = idFuente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<EstadistiquesFont> getEstadistiquesFont() {
        return estadistiquesFont;
    }

    public void setEstadistiquesFont(List<EstadistiquesFont> estadistiquesFont) {
        this.estadistiquesFont = estadistiquesFont;
    }
}

