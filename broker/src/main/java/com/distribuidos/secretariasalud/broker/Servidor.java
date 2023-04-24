package com.distribuidos.secretariasalud.broker;

public class Servidor {
    int puerto;
    String dominio;
    String nombre;
    int numRequest;//numero de peticiones hechas al server
    
    public Servidor(int puerto, String dominio, String nombre) {
        this.puerto = puerto;
        this.dominio = dominio;
        this.nombre = nombre;
    }
    public Servidor(int puerto, String nombre) {
        this.puerto = puerto;
        this.nombre = nombre;
    }
    public int getPuerto() {
        return puerto;
    }
    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDominio() {
        return dominio;
    }
    public void setDominio(String dominio) {
        this.dominio = dominio;
    }
    public int getNumRequest() {
        return numRequest;
    }
    public void setNumRequest(int numRequest) {
        this.numRequest = numRequest;
    }

    
    
}
