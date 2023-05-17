package com.distribuidos.secretariasalud.expedientes.modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "consultas")
public class Consulta {
    @Id
    private String id;
    private String fecha;
    private Usuario usuario;
    private String diagnostico;
    private String descripcion;
    
    public Consulta() {
    }

    public Consulta(String id, String fecha, Usuario usuario, String diagnostico, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.usuario = usuario;
        this.diagnostico = diagnostico;
        this.descripcion = descripcion;
    }

    public Consulta(Consulta pregunta) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
}
