/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.NAM.Security.Dto;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Aranthiel
 */
public class JwtDto {
    private String token;
    private String bearer ="Bearer";
    private String NombreUsuario;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, String NombreUsuario, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.NombreUsuario = NombreUsuario;
        this.authorities = authorities;
    }
    
    //getters y setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    
    
    
    
}
