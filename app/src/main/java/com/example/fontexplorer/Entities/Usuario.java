package com.example.fontexplorer.Entities;

public class Usuario {

    private Long idUsuario;

    private String nombre;

    private String apellidos;

    private String email;

    private String usuario;

    private String contraseña;

    public Usuario() {

    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Usuario id: ").append(idUsuario)
                .append("\nNom Usuari: ").append(usuario)
                .append("\nNom: ").append(nombre)
                .append("\nCognom: ").append(apellidos)
                .append("\nEmail: ").append(email)
                .append("\nContrasenya: ").append(contraseña);
        return stringBuilder.toString();
    }
}
