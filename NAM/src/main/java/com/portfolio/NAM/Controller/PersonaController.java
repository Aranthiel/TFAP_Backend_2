/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.NAM.Controller;

import com.portfolio.NAM.Interface.IPersonaService;
import com.portfolio.NAM.Entity.Persona;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Aranthiel
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@Api(tags="Persona")
public class PersonaController {
    @Autowired IPersonaService ipersonaService;
    
    @GetMapping("/personas/traer")
    public List<Persona> getPersona(){
        return ipersonaService.getPersona();
    }
    
    @PostMapping ("/personas/crear")
    public String createPersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "La persona ha sido creada correctamente";
    }
    
    @DeleteMapping ("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        ipersonaService.deletePersona(id);
        return "La persona ha sido eliminada correctamente";
    }
    
    //editar un usuario
    @PutMapping("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id, 
                                @RequestParam("nombre") String nuevoNombre,
                                @RequestParam("titulo") String nuevoTitulo,
                                @RequestParam("about") String nuevoAbout,
                                @RequestParam("email") String nuevoEmail,
                                @RequestParam("URL_Linkedin") String nuevoURL_Linkedin,
                                @RequestParam("URL_GitHub") String nuevoURL_GitHub,
                                @RequestParam("URL_Banner") String nuevoURL_Banner,
                                @RequestParam("contrasenia") String nuevaContrasenia){
        Persona persona=ipersonaService.findPersona(id);
        persona.setNombre(nuevoNombre);
        persona.setTitulo(nuevoTitulo);
	persona.setEmail(nuevoEmail);
	persona.setURL_Linkedin(nuevoURL_Linkedin);
	persona.setURL_GitHub(nuevoURL_GitHub);
	persona.setURL_Banner(nuevoURL_Banner);
	persona.setAbout(nuevoAbout);
        persona.setContrasenia(nuevaContrasenia);
        
        ipersonaService.savePersona(persona);
        return persona; 
    }
    
    @GetMapping("/personas/traer/perfil")
    public Persona findPersona(){
        return ipersonaService.findPersona((long)1);
    }

}
