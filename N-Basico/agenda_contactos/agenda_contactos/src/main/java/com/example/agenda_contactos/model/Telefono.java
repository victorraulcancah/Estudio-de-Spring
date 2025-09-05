package com.example.agenda_contactos.model;

public class Telefono {
    private Integer idTelefono;
    private Integer idContacto;
    private String tipo;
    private String numero;

    public Integer getIdTelefono() { return idTelefono; }
    public void setIdTelefono(Integer idTelefono) { this.idTelefono = idTelefono; }

    public Integer getIdContacto() { return idContacto; }
    public void setIdContacto(Integer idContacto) { this.idContacto = idContacto; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
}
