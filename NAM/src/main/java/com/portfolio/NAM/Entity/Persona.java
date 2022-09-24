/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.NAM.Entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Aranthiel
 */
@Getter @Setter
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min=1, max=100, message="Error, el campo mombre no cumple con la longitud")
    private String nombre;
    
    @NotNull
    @Size(min=1, max=100, message="Error, el campo titulo no cumple con la longitud")
    private String titulo;
    
    @NotNull
    @Size(min=1, max=10000, message="Error, el campo about no cumple con la longitud")
    private String about;
    
    @Size(min=1, max=100, message="Error, el campo email no cumple con la longitud")
    private String email;
    
    
    @Size(min=1, max=100, message="Error, el campo linkedin no cumple con la longitud")
    private String URL_Linkedin;
    
    
    @Size(min=1, max=100, message="Error, el campo github no cumple con la longitud")
    private String URL_GitHub;
    
   
    @Size(min=1, max=100, message="Error, el campo banner no cumple con la longitud")
    private String URL_Banner;
    
    @Size(min=1, max=100, message="Error, el campo contrasenia no cumple con la longitud")
    private String contrasenia;
    
    
    
    
    
}

