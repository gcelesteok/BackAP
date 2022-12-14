
package com.Proyecto.Portfolio.Repository;

import com.Proyecto.Portfolio.Entity.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;



public interface REducacion extends JpaRepository<Educacion, Integer>{
    public Optional<Educacion> findByNombreEducacion(String nombreEducacion);
    public boolean existsByNombreEducacion(String nombreEducacion);
    
}
