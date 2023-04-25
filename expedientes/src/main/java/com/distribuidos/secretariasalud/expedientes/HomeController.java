package com.distribuidos.secretariasalud.expedientes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/home")
public class HomeController {
    
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

}
