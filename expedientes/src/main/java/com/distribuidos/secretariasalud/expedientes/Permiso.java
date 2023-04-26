package com.distribuidos.secretariasalud.expedientes;

import java.util.Date;

public class Permiso {
    
    String id;
    String idDoctor;
    String idPaciente;
    Date fecha;
    public Permiso(String idDoctor, String idPaciente, Date fecha) {
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
        this.fecha = fecha;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getIdDoctor() {
        return idDoctor;
    }
    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }
    public String getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    @Override
    public String toString() {
        return "Permiso [idDoctor=" + idDoctor + ", idPaciente=" + idPaciente + ", fecha=" + fecha + "]";
    }

    
}
