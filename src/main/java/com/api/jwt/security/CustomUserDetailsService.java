package com.api.jwt.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Aquí deberías buscar el usuario en tu base de datos o cualquier otra fuente.
        // Por ahora, retornaremos un usuario hardcodeado para propósitos de ejemplo.
        if ("user".equals(username)) {
            return org.springframework.security.core.userdetails.User
                    .withUsername("user")
                    .password("{noop}password") // {noop} es para indicar que no se necesita codificación de contraseña
                    .authorities(new ArrayList<>()) // Añade roles o permisos si es necesario
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }

}
