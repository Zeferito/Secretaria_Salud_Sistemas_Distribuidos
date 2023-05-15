package com.distribuidos.secretariasalud.expedientes.controlllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.distribuidos.secretariasalud.expedientes.modelos.Permiso;
import com.distribuidos.secretariasalud.expedientes.repositorios.RepositorioPermisos;

@RestController
@RequestMapping("/home")
public class HomeController {
    
    @Autowired
    RepositorioPermisos repositorioPermisos;

    @GetMapping("")
    public ModelAndView HomeView(){
        
        return new ModelAndView("home");
    }
    @GetMapping("/doctor")
    public ModelAndView RegistrarDoctorView(){
        
        return new ModelAndView("home-doctor");
    }

    @GetMapping("/paciente")
    public ModelAndView RegistrarPacienteView(){
        
        return new ModelAndView("home-paciente");
    }

    @PostMapping("/aceptar-solicitud/{idSolicitud}")
    public ResponseEntity aceptarPermiso( @PathVariable("idSolicitud")String idPermiso){
        Permiso permiso = repositorioPermisos.findById(idPermiso).orElse(null);
        if(permiso!=null){
            permiso.setEstado("aceptado");
            repositorioPermisos.save(permiso);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    
    @PostMapping("/rechazar-solicitud/{idSolicitud}")
    public ResponseEntity rechazarSolicitud( @PathVariable("idSolicitud")String idPermiso){
        Permiso permiso = repositorioPermisos.findById(idPermiso).orElse(null);
        if(permiso!=null){
            permiso.setEstado("rechazada");
            repositorioPermisos.save(permiso);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/permisos/{id}")
    public ArrayList<Permiso> getPermisosPorPaciente(@PathVariable("id")String idPaciente){
        return repositorioPermisos.getPermisosByIdPaciente(idPaciente);
    }

}
