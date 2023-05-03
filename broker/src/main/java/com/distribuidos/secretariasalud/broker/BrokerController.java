package com.distribuidos.secretariasalud.broker;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        server.setNumRequest(server.getNumRequest()+1);
        return new ModelAndView("redirect:http://"+server.getDominio()+":"+server.getPuerto()+"/autenticacion");
    }

    @GetMapping("/registrar")
    public ModelAndView RegistrarUsuario(){

        //obtener direccion del server
        Servidor server=servidores.findAuthServer();
        server.setNumRequest(server.getNumRequest()+1);
        return new ModelAndView("redirect:http://"+server.getDominio()+":"+server.getPuerto()+"/autenticacion");
    }

    @GetMapping("/home")
    public ModelAndView ConsultarHome(@ModelAttribute("rol") String rol){

        //obtener direccion del server
        Servidor server=servidores.findExpedienteServer();
        server.setNumRequest(server.getNumRequest()+1);
        //añadir cliente a lista
   /*      String auth = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        httpResponse.addHeader(HttpHeaders.AUTHORIZATION, auth);
 */
        //crear cola para comunicación

       // String uRol = (String) modelo.asMap().get("rol");
        return new ModelAndView("redirect:http://"+server.getDominio()+":"+server.getPuerto()+"/home");
    }



}
