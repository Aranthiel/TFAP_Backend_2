/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.NAM.Security.Service;

import com.portfolio.NAM.Security.Entity.Usuario;
import com.portfolio.NAM.Security.Entity.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *
 * @author Aranthiel
 */
@Service
@Transactional
public class UserDetailsImpl implements UserDetailsService {
    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String nmobreUsuario) throws UsernameNotFoundException {
        Usuario usuario =usuarioService.getByNombreUsuario(nmobreUsuario).get();
        return UsuarioPrincipal.build(usuario);
    }
    
}
