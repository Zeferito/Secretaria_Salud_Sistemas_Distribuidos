package com.distribuidos.secretariasalud.expedientes.controlllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.distribuidos.secretariasalud.expedientes.ExpedientesApplication;
import com.distribuidos.secretariasalud.expedientes.modelos.Permiso;
import com.distribuidos.secretariasalud.expedientes.modelos.Usuario;
import com.distribuidos.secretariasalud.expedientes.repositorios.RepositorioPermisos;
import com.distribuidos.secretariasalud.expedientes.repositorios.RepositorioUsuarios;
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

    @Autowired
    private RepositorioUsuarios repositorioUsuarios;
    
    @GetMapping("/solicitar-permiso")
    public ModelAndView solicitarPermiso(){
        ArrayList<Usuario> pacientes= repositorioUsuarios.findByRol("paciente");
        return new ModelAndView("solicitar-permiso").addObject("pacientes", pacientes);
    }

    @GetMapping("/pacientes/busqueda={paciente}")
    public HashMap<String,String> buscarPaciente(@PathVariable("paciente") String nombre){
        ArrayList<Usuario> pacientes= repositorioUsuarios.findByNombreContaining(nombre);
        HashMap<String,String> pacientesIds= new HashMap<String,String>();
        for(Usuario paciente:pacientes){
             pacientesIds.put(paciente.getId(), paciente.getNombre());   
        } 
        return pacientesIds;
    }
   
    @GetMapping("{idDoctor}/mis-pacientes")//regresaria lista de pacientes de los que puede ver su expediente
    public HashMap<String,String> buscarPacientes(@PathVariable("idDoctor")String idDoctor){
        ArrayList<Permiso> permisosSolicitados= repositorioPermisos.getPermisosByIdDoctor(idDoctor);
        HashMap<String,String> pacientesIds= new HashMap<String,String>();
        for(Permiso permiso:permisosSolicitados){

            Usuario paciente = repositorioUsuarios.findById(permiso.getIdPaciente()).orElse(null);
            if(paciente!=null&&permiso.getEstado().equals("aceptado")){
                 pacientesIds.put(permiso.getIdPaciente(), paciente.getNombre());   
            }
            
        } 
        return pacientesIds;
    }

    @GetMapping("/permisos/{idDoctor}")
    public ArrayList<Permiso> getPermisosSolicitados(@PathVariable("idDoctor")String idDoctor){
        return repositorioPermisos.getPermisosByIdDoctor(idDoctor);
    }

    @PostMapping("/permisos/solicitar/")
    public ModelAndView solicitarPermiso(@RequestBody Permiso permiso){

       /*  if(permiso2!=null){
            permiso=permiso2;
        } */
        //enviar permiso a la cola
        permiso.setFecha(new Date());
        permiso.setEstado("solicitado");
        
       
        rabbitTemplate.convertAndSend("expedientes", "solicitudes."+permiso.getIdPaciente(), gson.toJson(permiso));
        
        //guardar en bd
        repositorioPermisos.save(permiso);

        return null;
    }

}
