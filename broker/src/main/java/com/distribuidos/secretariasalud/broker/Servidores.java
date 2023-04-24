package com.distribuidos.secretariasalud.broker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class Servidores {


    @Autowired
    List<Servidor> servidores;

    public Servidores() {
        if(servidores==null){
            servidores=new ArrayList<>();
        }
    }

    public List<Servidor> getServidores() {
        return servidores;
    }

    public void setServidores(List<Servidor> servidores) {
        this.servidores = servidores;
    }

    public void addServidor(Servidor servidor){
        servidores.add(servidor);
    }

    public void removeServidor(Servidor servidor){
        servidores.remove(servidor);
    }

    public Servidor findAuthServer(){
        Servidor server=null;
        for(Servidor s:servidores){
            if(s.getNombre().equals("auth")){
                server = s;
                break;
            }
            
        }
        return server;
    }

    public Servidor findExpedienteServer(){
        Servidor server=null;
        for(Servidor s:servidores){
            if(s.getNombre().equals("expedientes")){
                if(server==null){
                    server = s;
                }
                else if(s.getNumRequest()<server.getNumRequest()){
                    server=s;
                }
            }
        }
        return server;
    }
}
