package com.distribuidos.secretariasalud.expedientes;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/home/doctor")
public class DoctorController {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
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

    @GetMapping("/permisos/solicitar/{idpaciente}")
    public ModelAndView solicitarPermiso(@PathVariable("idpaciente") String paciente){

        //enviar permiso a la cola

        rabbitTemplate.convertAndSend(ExpedientesApplication.topicExchangeName, "expediente.permisos."+paciente, new Permiso(null,paciente,new Date()).toString());
        
        return null;
    }

}
