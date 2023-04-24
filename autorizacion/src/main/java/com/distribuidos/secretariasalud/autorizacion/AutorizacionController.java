package com.distribuidos.secretariasalud.autorizacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class AutorizacionController {
    
    @Autowired
    RepositorioUsuarios RepoUsuarios;

    @GetMapping("/autenticacion")
    public ModelAndView autenticacionView(){
        return new ModelAndView("/user-autenticacion");
    }
    @GetMapping("/login")
    public ModelAndView iniciarSesion(Usuario usuario, RedirectAttributes redirectAttributes){
        
        Usuario userFind= RepoUsuarios.save(usuario);
        if(usuario.getPassword().equals(userFind.getPassword())){

        //redirectAttributes.addFlashAttribute("rol", userFind.getRol());
           return new ModelAndView("redirect:http://localhost:8080/home"); 
        }
        return null;
    }

    @GetMapping("/registrar-doctor")
    public ModelAndView RegistrarDoctorView(){
        
        return new ModelAndView("registrar-doctor");
    }

    @GetMapping("/registrar-paciente")
    public ModelAndView RegistrarPacienteView(){
        
        return new ModelAndView("registrar-paciente");
    }
    @PostMapping("/registrar-doctor")
    public ModelAndView RegistrarDoctor(Usuario usuario){
        //consultar y validar cédula en https://cedulaprofesional.sep.gob.mx/cedula/buscaCedulaJson.action
        usuario.setRol("doctor");
        RepoUsuarios.save(usuario);
         
        return new ModelAndView("redirect:http://localhost:8080/home").addObject("rol", usuario.getRol());
    }

    @PostMapping("/registrar-paciente")
    public ModelAndView RegistrarUsuario(@RequestBody Usuario usuario){
        usuario.setRol("paciente");
        RepoUsuarios.save(usuario);
        return new ModelAndView("redirect:http://localhost:8080/home").addObject("rol", usuario.getRol());
    }




}
