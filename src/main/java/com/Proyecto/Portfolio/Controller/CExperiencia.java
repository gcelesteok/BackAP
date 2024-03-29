
package com.Proyecto.Portfolio.Controller;

import com.Proyecto.Portfolio.Dto.DtoExperiencia;
import com.Proyecto.Portfolio.Entity.Experiencia;
import com.Proyecto.Portfolio.Security.Controller.Mensaje;
import com.Proyecto.Portfolio.Service.SExperiencia;
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
@RequestMapping("experiencia-laboral")
@CrossOrigin(origins = "https://portfolio-acg-969d5.web.app/")
public class CExperiencia {
    
    @Autowired
    SExperiencia sExperiencia;

    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list() {
        List<Experiencia> list = sExperiencia.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoExperiencia dtoexp) {
        if (StringUtils.isBlank(dtoexp.getNombreExperiencia())) {
            return new ResponseEntity(new Mensaje("Debes ingresar el nombre"), HttpStatus.BAD_REQUEST);
        }
        if (sExperiencia.existByNombreExperiencia(dtoexp.getNombreExperiencia())) {
            return new ResponseEntity(new Mensaje("Ya existe esa experiencia"), HttpStatus.BAD_REQUEST);
        }
        Experiencia experiencia = new Experiencia(dtoexp.getNombreExperiencia(), dtoexp.getDescripcionExperiencia(), dtoexp.getCompania(), dtoexp.getImgExp(), dtoexp.getStartExp(), dtoexp.getEndExp());
        sExperiencia.save(experiencia);

        return new ResponseEntity(new Mensaje("Se agregó correctamente!"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoExperiencia dtoexp) {
        if (!sExperiencia.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe ese ID"), HttpStatus.BAD_REQUEST);
        }
        if (sExperiencia.existByNombreExperiencia(dtoexp.getNombreExperiencia()) && sExperiencia.getByNombreExperiencia(dtoexp.getNombreExperiencia()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ya existe esa experiencia"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoexp.getNombreExperiencia())) {
            return new ResponseEntity(new Mensaje("Debes ingresar el nombre"), HttpStatus.BAD_REQUEST);
        }

        Experiencia experiencia = sExperiencia.getOne(id).get();
        experiencia.setNombreExperiencia(dtoexp.getNombreExperiencia());
        experiencia.setDescripcionExperiencia(dtoexp.getDescripcionExperiencia());
        experiencia.setCompania(dtoexp.getCompania());
        experiencia.setImgExp(dtoexp.getImgExp());
        experiencia.setStartExp(dtoexp.getStartExp());
        experiencia.setEndExp(dtoexp.getEndExp());

        sExperiencia.save(experiencia);

        return new ResponseEntity(new Mensaje("Se actualizó ok la experiencia"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sExperiencia.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe ese ID"), HttpStatus.BAD_REQUEST);
        }

        sExperiencia.delete(id);

        return new ResponseEntity(new Mensaje("Se eliminó la experiencia"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id) {
        if (!sExperiencia.existById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Experiencia experiencia = sExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
}
