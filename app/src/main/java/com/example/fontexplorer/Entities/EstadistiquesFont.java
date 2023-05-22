package com.example.fontexplorer.Entities;

public class EstadistiquesFont {
    private Long idEstadistica;

    private Fuente fuente;

    private float calificacion;

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
        return "EstadistiquesFont{" +
                "idEstadistica=" + idEstadistica +
                ", fuente=" + fuente +
                ", calificacion=" + calificacion +
                ", comentarios='" + comentarios + '\'' +
                '}';
    }
}

