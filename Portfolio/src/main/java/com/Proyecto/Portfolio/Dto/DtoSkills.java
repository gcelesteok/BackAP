
package com.Proyecto.Portfolio.Dto;

import javax.validation.constraints.NotBlank;


public class DtoSkills {
    @NotBlank
    private String habilidad;
    @NotBlank
    private int alcance;

    public DtoSkills() {
    }

    public DtoSkills(String habilidad, int alcance) {
        this.habilidad = habilidad;
        this.alcance = alcance;
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
