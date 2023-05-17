package com.distribuidos.secretariasalud.expedientes.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.distribuidos.secretariasalud.expedientes.modelos.Expedientes;

public interface RepositorioExpedientes extends MongoRepository<Expedientes, String> {
    //public Expedientes findByCorreo(String expediente);
}
