package com.gft.controleStartersGFT.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gft.controleStartersGFT.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{

	Usuario findByLogin(String login);
}
