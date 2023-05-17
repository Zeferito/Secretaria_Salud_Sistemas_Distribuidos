package com.distribuidos.secretariasalud.expedientes.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.distribuidos.secretariasalud.expedientes.modelos.Consulta;

public interface RepositorioConsulta extends MongoRepository<Consulta, String> {
    public Consulta findByCorreo(String consulta);
}
