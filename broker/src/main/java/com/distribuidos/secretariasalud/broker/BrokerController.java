package com.distribuidos.secretariasalud.broker;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BrokerController {
    
    Servidores servidores;
    public BrokerController(){
        if(servidores==null){
            servidores = new Servidores();
            servidores.addServidor(new Servidor(8081, "localhost","auth"));
            servidores.addServidor(new Servidor(8082, "localhost","expedientes"));
        }
    }

    @GetMapping("/login")
    public ModelAndView iniciarSesion(){

        //obtener direccion del server
        Servidor server=servidores.findAuthServer();

        return new ModelAndView("redirect:http://"+server.getDominio()+":"+server.getPuerto()+"/autenticacion");
    }

    @GetMapping("/registrar")
    public ModelAndView RegistrarUsuario(){

        //obtener direccion del server
        Servidor server=servidores.findAuthServer();

        return new ModelAndView("redirect:http://"+server.getDominio()+":"+server.getPuerto()+"/autenticacion");
    }

    @GetMapping("/home")
    public ModelAndView ConsultarHome(@ModelAttribute("rol") String rol){

        //obtener direccion del server
        Servidor server=servidores.findExpedienteServer();

       // String uRol = (String) modelo.asMap().get("rol");
        return new ModelAndView("redirect:http://"+server.getDominio()+":"+server.getPuerto()+"/home");
    }



}
