/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.NAM.Interface;

import com.portfolio.NAM.Entity.Persona;
import java.util.List;

/**
 *
 * @author Aranthiel
 */
public interface IPersonaService {
    //traer lista de popjetos cde clase Persona
    public List<Persona> getPersona();
    
    //Guardar objeto tipo Persona
    public void savePersona(Persona persona);
    
    //eliminar objeto tipo Persona por Id
    public void deletePersona(long id);
    
    //Buscar un objeto Persona por ID
    public Persona findPersona(Long id);
    
}
