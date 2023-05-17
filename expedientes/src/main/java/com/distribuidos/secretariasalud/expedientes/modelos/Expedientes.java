package com.distribuidos.secretariasalud.expedientes.modelos;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "expedientes")
public class Expedientes {
    @Id
    private String id;
    private String nombre;
    private Consulta consulta;
    private List<byte[]> archivosPDF;
    private List<byte[]> archivosImagenes;
    
    public Expedientes() {
    }

    public Expedientes(String id, String nombre, Consulta consulta, List<byte[]> archivosPDF,
            List<byte[]> archivosImagenes) {
        this.id = id;
        this.nombre = nombre;
        this.consulta = consulta;
        this.archivosPDF = archivosPDF;
        this.archivosImagenes = archivosImagenes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public List<byte[]> getArchivosPDF() {
        return archivosPDF;
    }

    public void setArchivosPDF(List<byte[]> archivosPDF) {
        this.archivosPDF = archivosPDF;
    }

    public List<byte[]> getArchivosImagenes() {
        return archivosImagenes;
    }

    public void setArchivosImagenes(List<byte[]> archivosImagenes) {
        this.archivosImagenes = archivosImagenes;
    }
}
