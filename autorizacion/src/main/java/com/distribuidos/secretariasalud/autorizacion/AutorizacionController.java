package com.distribuidos.secretariasalud.autorizacion;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.distribuidos.secretariasalud.autorizacion.seguridad.JwtService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AutorizacionController {
    
    @Autowired
    RepositorioUsuarios RepoUsuarios;
    
    private final JwtService jwtService;
    
    private final PasswordEncoder passEncoder;

    private final AuthenticationManager authenticationManager;

    private final String PREFIX = "Bearer ";
    @GetMapping("/autenticacion")
    public ModelAndView autenticacionView(){
        return new ModelAndView("/user-autenticacion");
    }
    @PostMapping("/login")
    public ModelAndView iniciarSesion(@RequestBody Usuario usuario, HttpServletResponse response){
        
       /*  Usuario userFind= RepoUsuarios.save(usuario);
        if(usuario.getPassword().equals(userFind.getPassword())){


        //redirectAttributes.addFlashAttribute("rol", userFind.getRol());
           //return new ModelAndView("redirect:http://localhost:8080/home"); 
        } */
        
        Usuario usuarioFind = RepoUsuarios.findByCorreo(usuario.getCorreo());            
        
        if(passEncoder.matches(usuario.getPassword(), usuarioFind.getPassword())){

            response.addHeader(HttpHeaders.AUTHORIZATION, PREFIX+jwtService.generateToken(usuarioFind));
            return new ModelAndView("redirect:http://localhost:8080/home");
        }
        return new ModelAndView("redirect:/error");

    }

    @PostMapping("/login/movil")
    public ResponseEntity iniciarSesionMovil(@RequestBody Usuario usuario, HttpServletResponse response){
        
       /*  Usuario userFind= RepoUsuarios.save(usuario);
        if(usuario.getPassword().equals(userFind.getPassword())){


        //redirectAttributes.addFlashAttribute("rol", userFind.getRol());
           //return new ModelAndView("redirect:http://localhost:8080/home"); 
        } */
        
        Usuario usuarioFind = RepoUsuarios.findByCorreo(usuario.getCorreo());            
        
        if(passEncoder.matches(usuario.getPassword(), usuarioFind.getPassword())){

            response.addHeader(HttpHeaders.AUTHORIZATION, PREFIX+jwtService.generateToken(usuarioFind));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(403).build();

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
    public ModelAndView RegistrarDoctor(@RequestBody Usuario usuario, HttpServletResponse response){
        //consultar y validar cédula en https://cedulaprofesional.sep.gob.mx/cedula/buscaCedulaJson.action
        usuario.setRol("doctor");
        usuario.setPassword(passEncoder.encode(usuario.getPassword()));
        RepoUsuarios.save(usuario);
        
        response.addHeader(HttpHeaders.AUTHORIZATION, PREFIX+jwtService.generateToken(usuario));
        return new ModelAndView("redirect:http://localhost:8080/home");
        //return new ModelAndView("redirect:http://localhost:8080/home").addObject("usuario", usuario);
    }

    @PostMapping("/registrar-paciente")
    public ModelAndView RegistrarUsuario(@RequestBody Usuario usuario, HttpServletResponse response){
        usuario.setRol("paciente");
        usuario.setPassword(passEncoder.encode(usuario.getPassword()));
        RepoUsuarios.save(usuario);
        response.addHeader(HttpHeaders.AUTHORIZATION, PREFIX+jwtService.generateToken(usuario));
        return new ModelAndView("redirect:http://localhost:8080/home");
    }

    @PostMapping("/registrar-paciente/movil")
    public ResponseEntity RegistrarUsuarioMovil(@RequestBody Usuario usuario, HttpServletResponse response){
        usuario.setRol("paciente");
        usuario.setPassword(passEncoder.encode(usuario.getPassword()));
        RepoUsuarios.save(usuario);
        response.addHeader(HttpHeaders.AUTHORIZATION, PREFIX+jwtService.generateToken(usuario));
        return ResponseEntity.ok().build();
    }




}
