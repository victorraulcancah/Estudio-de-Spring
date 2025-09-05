package com.example.agenda_contactos.model;

public class Correo {
    private Integer idCorreo;
    private Integer idContacto;
    private String tipo;
    private String email;

    public Integer getIdCorreo() { return idCorreo; }
    public void setIdCorreo(Integer idCorreo) { this.idCorreo = idCorreo; }

    public Integer getIdContacto() { return idContacto; }
    public void setIdContacto(Integer idContacto) { this.idContacto = idContacto; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
