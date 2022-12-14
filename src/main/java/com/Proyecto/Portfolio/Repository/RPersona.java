    
package com.Proyecto.Portfolio.Repository;

import com.Proyecto.Portfolio.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RPersona extends JpaRepository<Persona,Integer> {
    public Optional<Persona> findByNombrePersona(String nombrePersona);
    public boolean existsByNombrePersona(String nombrePersona);
   
}
