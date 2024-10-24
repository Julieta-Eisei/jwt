package com.api.jwt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.jwt.model.Usuario;

@Service
public class UserService {
	
	private List<Usuario> users = new ArrayList<>();
	
	public UserService() {
        // Hardcoding algunos usuarios para pruebas
        users.add(new Usuario("user", "password"));
        users.add(new Usuario("user2", "password2"));
    }
	
    public Usuario findByUsername(String username) {
    	return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null); // Devuelve null si no se encuentra el usuario
        // Implementa la lógica para buscar un usuario en la base de datos
    }

    public Usuario save(Usuario user) {
    	users.add(user); // Agrega el usuario a la lista
        return user;
        // Implementa la lógica para guardar un usuario en la base de datos
    }
}
