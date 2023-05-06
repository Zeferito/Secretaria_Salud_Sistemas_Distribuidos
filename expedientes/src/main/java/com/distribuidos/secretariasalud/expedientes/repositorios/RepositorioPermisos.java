package com.distribuidos.secretariasalud.expedientes.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.distribuidos.secretariasalud.expedientes.modelos.Permiso;

public interface RepositorioPermisos extends MongoRepository<Permiso,String>{
    
}
