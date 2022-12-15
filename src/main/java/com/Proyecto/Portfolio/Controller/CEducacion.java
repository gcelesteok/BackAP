package com.Proyecto.Portfolio.Controller;

import com.Proyecto.Portfolio.Dto.DtoEducacion;
import com.Proyecto.Portfolio.Entity.Educacion;
import com.Proyecto.Portfolio.Security.Controller.Mensaje;
import com.Proyecto.Portfolio.Service.SEducacion;
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
@RequestMapping("educacion")
@CrossOrigin(origins = "https://portfolio-acg-969d5.web.app/")
public class CEducacion {
    
    @Autowired
    SEducacion sEducacion;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoedu) {
        if (StringUtils.isBlank(dtoedu.getNombreEducacion())) {
            return new ResponseEntity(new Mensaje("Debes ingresar el nombre"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = new Educacion(
                dtoedu.getNombreEducacion(),
                dtoedu.getDescripcionEducacion(),
                dtoedu.getInstitucion(),
                dtoedu.getImagenE());
           
        sEducacion.save(educacion);

        return new ResponseEntity(new Mensaje("Se agregó correctamente!"), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoEducacion dtoedu) {
        if (!sEducacion.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe ese ID"), HttpStatus.BAD_REQUEST);
        }
        if (sEducacion.existByNombreEducacion(dtoedu.getNombreEducacion()) && sEducacion.getByNombreEducacion(dtoedu.getNombreEducacion()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ya existe esa educacion"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoedu.getNombreEducacion())) {
            return new ResponseEntity(new Mensaje("Debes ingresar el nombre"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = sEducacion.getOne(id).get();
        educacion.setNombreEducacion(dtoedu.getNombreEducacion());
        educacion.setDescripcionEducacion(dtoedu.getDescripcionEducacion());
        educacion.setInstitucion(dtoedu.getInstitucion());
        educacion.setImagenE(dtoedu.getImagenE());
        
        sEducacion.save(educacion);

        return new ResponseEntity(new Mensaje("Se añadió correctamente la educación"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sEducacion.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe ese ID"), HttpStatus.BAD_REQUEST);
        }

        sEducacion.delete(id);

        return new ResponseEntity(new Mensaje("Se eliminó la educacion"), HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
        if (!sEducacion.existById(id)) {
            return new ResponseEntity(new Mensaje("no existe el ID"), HttpStatus.NOT_FOUND);
        }

        Educacion educacion = sEducacion.getOne(id).get();

        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    
}