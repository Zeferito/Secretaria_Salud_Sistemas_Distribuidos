package com.distribuidos.secretariasalud.broker;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class BrokerController {
    
    private final RestTemplate restTemplate;

    Servidores servidores;
    public BrokerController(){
        restTemplate=new RestTemplateBuilder().build();
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

    @PostMapping("/registrar-paciente")
    public ResponseEntity<String> RegistrarUsuario(@RequestBody String body){

        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<String>(body, headers);

        Servidor server=servidores.findAuthServer();
        ResponseEntity<String> response = this.restTemplate.postForEntity("http://"+server.getDominio()+":"+server.getPuerto()+"/registrar-paciente", request, String.class);
        //obtener direccion del server
      
        server.setNumRequest(server.getNumRequest()+1);
        return response;
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
