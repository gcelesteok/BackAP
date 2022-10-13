
package com.Proyecto.Portfolio.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Skills {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String habilidad;
    private int alcance;

    public Skills() {
    }

    public Skills(String habilidad, int alcance) {
        this.habilidad = habilidad;
        this.alcance = alcance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public int getAlcance() {
        return alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }
    
    
    
    
}
