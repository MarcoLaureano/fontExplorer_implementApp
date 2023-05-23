package com.example.fontexplorer.Entities;

import com.google.gson.annotations.SerializedName;

public class EstadistiquesFont {
    @SerializedName("idEstadistica")
    private Long idEstadistica;

    @SerializedName("fuente")
    private Fuente fuente;

    @SerializedName("calificacion")
    private float calificacion;

    @SerializedName("comentarios")
    private String comentarios;

    public EstadistiquesFont () {}

    public EstadistiquesFont( Fuente fuente, float calificacion, String comentarios) {
        this.fuente = fuente;
        this.calificacion = calificacion;
        this.comentarios = comentarios;
    }

    public Long getIdEstadistica() {
        return idEstadistica;
    }

    public void setIdEstadistica(Long idEstadistica) {
        this.idEstadistica = idEstadistica;
    }

    public Fuente getFuente() {
        return fuente;
    }

    public void setFuente(Fuente fuente) {
        this.fuente = fuente;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        return calificacion + ", " +
                 comentarios
                ;
    }
}

