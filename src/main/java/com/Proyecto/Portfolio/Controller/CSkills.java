
package com.Proyecto.Portfolio.Controller;

import com.Proyecto.Portfolio.Dto.DtoSkills;
import com.Proyecto.Portfolio.Entity.Skills;
import com.Proyecto.Portfolio.Security.Controller.Mensaje;
import com.Proyecto.Portfolio.Service.SSkills;
import java.util.List;
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
@RequestMapping("skills")
@CrossOrigin(origins = "https://portfolio-acg-969d5.web.app/")
public class CSkills {
    
    @Autowired
    SSkills sSkills;

    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list() {
        List<Skills> list = sSkills.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoSkills dtosk) {
        if (StringUtils.isBlank(dtosk.getHabilidad())) {
            return new ResponseEntity(new Mensaje("Debes ingresar Habilidad"), HttpStatus.BAD_REQUEST);
        }
        if (sSkills.existByHabilidad(dtosk.getHabilidad())) {
            return new ResponseEntity(new Mensaje("Ya existe la skill"), HttpStatus.BAD_REQUEST);
        }

        Skills skill = new Skills(
                dtosk.getHabilidad(),
                dtosk.getAlcance()
        );

        sSkills.save(skill);
        return new ResponseEntity(new Mensaje("Se añadió correctamente la skill!"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoSkills dtosk) {
        if (!sSkills.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe ese ID"), HttpStatus.BAD_REQUEST);
        }
        if (sSkills.existByHabilidad(dtosk.getHabilidad()) && sSkills.getByHabilidad(dtosk.getHabilidad()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ya existe la skill"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtosk.getHabilidad())){
            return new ResponseEntity(new Mensaje("Debes ingresar el nombre de la habilidad"), HttpStatus.BAD_REQUEST);
        }
        
        Skills skill = sSkills.getOne(id).get();
        skill.setHabilidad(dtosk.getHabilidad());
        skill.setAlcance(dtosk.getAlcance());
        
        sSkills.save(skill);
        
        return new ResponseEntity(new Mensaje("Se actualizó ok el skill"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sSkills.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe ese ID"), HttpStatus.BAD_REQUEST);
        }

        sSkills.delete(id);
        return new ResponseEntity(new Mensaje("Se eliminó el skill"), HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id) {
        if (!sSkills.existById(id)){
            return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);
        }
        
        Skills skill = sSkills.getOne(id).get();
        
        return new ResponseEntity(skill, HttpStatus.OK);
    }
}
