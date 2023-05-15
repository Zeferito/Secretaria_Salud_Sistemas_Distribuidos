package com.distribuidos.secretariasalud.expedientes.controlllers;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.distribuidos.secretariasalud.expedientes.ExpedientesApplication;
import com.distribuidos.secretariasalud.expedientes.modelos.Permiso;
import com.distribuidos.secretariasalud.expedientes.repositorios.RepositorioPermisos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private Gson gson;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RepositorioPermisos repositorioPermisos;
    
    @GetMapping("/pacientes/{paciente}")
    public ModelAndView buscarPaciente(@PathVariable("paciente") String nombre){
        return null;
    }
   
    @GetMapping("/mis-pacientes")//regresaria lista de pacientes de los que puede ver su expediente
    public ModelAndView buscarPacientes(){
        return null;
    }

    @GetMapping("/expedientes/{expediente}")
    public ModelAndView buscarExpediente(@PathVariable("expediente") String expediente){
        return null;
    }

    @PostMapping("/permisos/solicitar/")
    public ModelAndView solicitarPermiso(@RequestBody Permiso permiso){

        //enviar permiso a la cola
        permiso.setFecha(new Date());
        permiso.setEstado("solicitado");
        
       
        rabbitTemplate.convertAndSend("expedientes", "solicitudes."+permiso.getIdPaciente(), gson.toJson(permiso));
        
        //guardar en bd
        repositorioPermisos.save(permiso);

        return null;
    }

}
