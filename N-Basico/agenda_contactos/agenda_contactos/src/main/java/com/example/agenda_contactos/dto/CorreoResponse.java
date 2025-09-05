package com.example.agenda_contactos.dto;

public class CorreoResponse {
    private Integer idCorreo;
    private String tipo;
    private String email;

    public Integer getIdCorreo() { return idCorreo; }
    public void setIdCorreo(Integer idCorreo) { this.idCorreo = idCorreo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
