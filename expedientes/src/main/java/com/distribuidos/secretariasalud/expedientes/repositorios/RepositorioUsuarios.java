package com.distribuidos.secretariasalud.expedientes.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.distribuidos.secretariasalud.expedientes.modelos.Usuario;

public interface RepositorioUsuarios extends MongoRepository<Usuario, String> {

    public Usuario findByCorreo(String correo);
    
}
