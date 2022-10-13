
package com.Proyecto.Portfolio.Repository;

import com.Proyecto.Portfolio.Entity.Skills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RSkills extends JpaRepository<Skills,Integer>{
    public Optional<Skills> findByHabilidad(String habilidad);
    public boolean existsByHabilidad(String habilidad);
    
}
