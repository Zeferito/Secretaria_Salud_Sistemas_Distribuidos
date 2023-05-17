package com.distribuidos.secretariasalud.expedientes.controlllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribuidos.secretariasalud.expedientes.modelos.Consulta;
import com.distribuidos.secretariasalud.expedientes.modelos.Usuario;
import com.distribuidos.secretariasalud.expedientes.repositorios.RepositorioConsulta;

@Service
public class ConsultaService {
    private RepositorioConsulta consultaRepository;

    @Autowired
    public ConsultaService(RepositorioConsulta consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public Consulta guardarConsulta(String fecha, Usuario usuario, String diagnostico, String descripcion) {
        Consulta consulta = new Consulta();
        consulta.setFecha(fecha);
        consulta.setUsuario(usuario);
        consulta.setDiagnostico(diagnostico);
        consulta.setDescripcion(descripcion);

        return consultaRepository.save(consulta);
    }
}

