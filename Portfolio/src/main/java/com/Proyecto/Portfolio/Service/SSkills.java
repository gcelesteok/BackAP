
package com.Proyecto.Portfolio.Service;

import com.Proyecto.Portfolio.Entity.Skills;
import com.Proyecto.Portfolio.Repository.RSkills;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SSkills {
    @Autowired
    RSkills rSkills;
    
    public List<Skills> list(){
        return rSkills.findAll();
    }
    public Optional<Skills> getOne(int id){
        return rSkills.findById(id);
    }
    public Optional<Skills> getByHabilidad(String habilidad){
        return rSkills.findByHabilidad(habilidad);
        
    }
    public boolean existByHabilidad(String habilidad){
        return rSkills.existsByHabilidad(habilidad);
    }
    public void save(Skills skills){
        rSkills.save(skills);
    }
    public void delete(int id){
        rSkills.deleteById(id);
    }
    public boolean existById(int id){
        return rSkills.existsById(id);
    }
    
}
