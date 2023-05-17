package com.distribuidos.secretariasalud.expedientes.controlllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.distribuidos.secretariasalud.expedientes.modelos.Consulta;

@RestController
public class ConsultasController {
    private ConsultaService consultaService;

    @Autowired
    public ConsultasController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping("/consultas")
    public Consulta guardarConsulta(@RequestBody Consulta consultaRequest) {
        return consultaService.guardarConsulta(
            consultaRequest.getFecha(),
            consultaRequest.getUsuario(),
            consultaRequest.getDiagnostico(),
            consultaRequest.getDescripcion()
        );
    }
}
