/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.NAM.Security.Controller;

import com.portfolio.NAM.Security.Dto.JwtDto;
import com.portfolio.NAM.Security.Dto.LoginUsuario;
import com.portfolio.NAM.Security.Dto.NuevoUsuario;
import com.portfolio.NAM.Security.Entity.Rol;
import com.portfolio.NAM.Security.Entity.Usuario;
import com.portfolio.NAM.Security.Enums.RolNombre;
import com.portfolio.NAM.Security.Service.RolService;
import com.portfolio.NAM.Security.Service.UsuarioService;
import com.portfolio.NAM.Security.jwt.JwtProvider;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aranthiel
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin
@Api(tags = "Auth")
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;  
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UsuarioService usuarioService;
        
    @Autowired
    RolService rolService;
    
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario,BindingResult bindingResult){
    if (bindingResult.hasErrors())
        return new ResponseEntity(new Mensaje("Campos mal puestos o  email invalido"), HttpStatus.BAD_REQUEST);
    
    if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
        return new ResponseEntity(new Mensaje("El nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
    
    if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
        return new ResponseEntity(new Mensaje("El email ya existe"), HttpStatus.BAD_REQUEST);
    
    Usuario usuario = new Usuario( nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
    
    Set<Rol> roles = new HashSet<>();
    roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
    
    if (nuevoUsuario.getRoles().contains("admin"))
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
    usuario.setRoles(roles);
    usuarioService.save(usuario);
    
    return new ResponseEntity(new Mensaje("Usuario creado con éxito"), HttpStatus.CREATED);
    }
    
     @PostMapping("/login")
     public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario,BindingResult bindingResult){
         if (bindingResult.hasErrors()) {
             return new ResponseEntity(new Mensaje("Campos mal puestos o  email invalido"), HttpStatus.BAD_REQUEST);
         }
         Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),
                         loginUsuario.getPassword()));
         SecurityContextHolder.getContext().setAuthentication(authentication);
         String jwt = jwtProvider.generateToken(authentication);
         UserDetails userDetail = (UserDetails) authentication.getPrincipal();
         JwtDto jwtDto = new JwtDto(jwt, userDetail.getUsername(), userDetail.getAuthorities());
         return new ResponseEntity<>(jwtDto, HttpStatus.OK);
     }

}
