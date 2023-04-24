package com.distribuidos.secretariasalud.autorizacion;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioUsuarios extends MongoRepository<Usuario, String> {

    public Usuario findByNombre();
    
}
