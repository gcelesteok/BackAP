
package com.Proyecto.Portfolio.Controller;

import com.Proyecto.Portfolio.Dto.DtoProyectos;
import com.Proyecto.Portfolio.Entity.Proyectos;
import com.Proyecto.Portfolio.Security.Controller.Mensaje;
import com.Proyecto.Portfolio.Service.SProyectos;
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
@RequestMapping("proyectos")
@CrossOrigin(origins = "https://portfolio-acg-969d5.web.app/")
public class CProyectos {
    
    @Autowired
    SProyectos sProyectos;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list(){
        List<Proyectos> list = sProyectos.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProyectos dtoproy) {
        if(StringUtils.isBlank(dtoproy.getNombre())){
            return new ResponseEntity(new Mensaje("Debes ingresar el nombre"), HttpStatus.BAD_REQUEST);
        }
        if (sProyectos.existByNombre(dtoproy.getNombre())){
            return new ResponseEntity(new Mensaje("Ya existe esa experiencia"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyectos = new Proyectos(dtoproy.getNombre(),dtoproy.getImgProyecto(), dtoproy.getDescripcion(),dtoproy.getUrl());
        sProyectos.save(proyectos);
        
        return new ResponseEntity(new Mensaje("Se agregó correctamente!"), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProyectos dtoproy) {
        if (!sProyectos.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe ese ID"), HttpStatus.BAD_REQUEST);
        }
        if (sProyectos.existByNombre(dtoproy.getNombre()) && sProyectos.getByNombre(dtoproy.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ya existe ese proyecto"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoproy.getNombre())){
            return new ResponseEntity(new Mensaje("Debes ingresar el nombre"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyectos = sProyectos.getOne(id).get();
        proyectos.setNombre(dtoproy.getNombre());
        proyectos.setImgProyecto(dtoproy.getImgProyecto());
        proyectos.setDescripcion(dtoproy.getDescripcion());
        proyectos.setUrl(dtoproy.getUrl());
        
        sProyectos.save(proyectos);
        
        return new ResponseEntity(new Mensaje("Se actualizó correctamente el proyecto"), HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id){
        if (!sProyectos.existById(id)){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        
        Proyectos proyectos = sProyectos.getOne(id).get();
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sProyectos.existById(id)){
            return new ResponseEntity(new Mensaje("No existe ese ID"), HttpStatus.BAD_REQUEST);
            }
        
        sProyectos.delete(id);
        return new ResponseEntity(new Mensaje("Se eliminó el proyecto!"), HttpStatus.OK);
    }
}
