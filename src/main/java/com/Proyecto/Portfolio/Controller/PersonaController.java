package com.Proyecto.Portfolio.Controller;

import com.Proyecto.Portfolio.Dto.DtoPersona;
import com.Proyecto.Portfolio.Entity.Persona;
import com.Proyecto.Portfolio.Security.Controller.Mensaje;
import com.Proyecto.Portfolio.Service.SPersona;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("persona")
@CrossOrigin(origins = "https://portfolio-acg-969d5.web.app/")
public class PersonaController {

    @Autowired
    SPersona sPersona;

    @GetMapping("/traer-persona")
    public ResponseEntity findPersona() {
        Optional<Persona> myPers = sPersona.getOne(1);
        return new ResponseEntity(myPers, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoPersona dtoper) {
        if (StringUtils.isBlank(dtoper.getNombrePersona())) {
            return new ResponseEntity(new Mensaje("Debes ingresar el nombre"), HttpStatus.BAD_REQUEST);
        }
        if (sPersona.existByNombrePersona(dtoper.getNombrePersona())) {
            return new ResponseEntity(new Mensaje("Ya existe ese nombre"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = new Persona(
                dtoper.getNombrePersona(),
                dtoper.getPuestoPersona(),
                dtoper.getUbicacionPersona(),
                dtoper.getSobreMi(),
                dtoper.getImgPersona(),
                dtoper.getEmailPersona(),
                dtoper.getTelPersona(),
                dtoper.getGithubPersona(),
                dtoper.getLinkedinPersona());

        sPersona.save(persona);

        return new ResponseEntity(new Mensaje("Se agregó correctamente!"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sPersona.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe ese ID"), HttpStatus.BAD_REQUEST);
        }

        sPersona.delete(id);

        return new ResponseEntity(new Mensaje("Se eliminó la persona"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoPersona dtoper) {
        if (!sPersona.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe ese ID"), HttpStatus.BAD_REQUEST);
        }
        if (sPersona.existByNombrePersona(dtoper.getNombrePersona()) && sPersona.getByNombrePersona(dtoper.getNombrePersona()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ya existe esa persona"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoper.getNombrePersona())) {
            return new ResponseEntity(new Mensaje("Debes ingresar el nombre"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = sPersona.getOne(id).get();
        persona.setNombrePersona(dtoper.getNombrePersona());
        persona.setPuestoPersona(dtoper.getPuestoPersona());
        persona.setUbicacionPersona(dtoper.getUbicacionPersona());
        persona.setSobreMi(dtoper.getSobreMi());
        persona.setImgPersona(dtoper.getImgPersona());
        persona.setEmailPersona(dtoper.getEmailPersona());
        persona.setTelPersona(dtoper.getTelPersona());
        persona.setGithubPersona(dtoper.getGithubPersona());
        persona.setLinkedinPersona(dtoper.getLinkedinPersona());

        sPersona.save(persona);

        return new ResponseEntity(new Mensaje("Se actualizó correctamente la persona"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id) {
        if (!sPersona.existById(id)) {
            return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);
        }

        Persona persona = sPersona.getOne(id).get();

        return new ResponseEntity(persona, HttpStatus.OK);
    }

}
