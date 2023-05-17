package com.distribuidos.secretariasalud.expedientes.repositorios;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.distribuidos.secretariasalud.expedientes.modelos.Usuario;

public interface RepositorioUsuarios extends MongoRepository<Usuario, String> {

    public Usuario findByCorreo(String correo);
    public ArrayList<Usuario> findByRol(String rol);
    public ArrayList<Usuario> findByNombreContaining(String nombre);
}
