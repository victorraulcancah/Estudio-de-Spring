package com.example.agenda_contactos.dto;

public class ContactoGrupoResponse {
    private int idContacto;
    private String nombreContacto;
    private int idGrupo;
    private String nombreGrupo;

    public ContactoGrupoResponse() {}

    public ContactoGrupoResponse(int idContacto, String nombreContacto, int idGrupo, String nombreGrupo) {
        this.idContacto = idContacto;
        this.nombreContacto = nombreContacto;
        this.idGrupo = idGrupo;
        this.nombreGrupo = nombreGrupo;
    }

    public int getIdContacto() { return idContacto; }
    public void setIdContacto(int idContacto) { this.idContacto = idContacto; }

    public String getNombreContacto() { return nombreContacto; }
    public void setNombreContacto(String nombreContacto) { this.nombreContacto = nombreContacto; }

    public int getIdGrupo() { return idGrupo; }
    public void setIdGrupo(int idGrupo) { this.idGrupo = idGrupo; }

    public String getNombreGrupo() { return nombreGrupo; }
    public void setNombreGrupo(String nombreGrupo) { this.nombreGrupo = nombreGrupo; }
}
