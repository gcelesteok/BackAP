
package com.Proyecto.Portfolio.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
   
    private String nombrePersona;
    
    private String puestoPersona;
    
    private String ubicacionPersona;
    
    private String sobreMi;
    
    private String imgPersona;
   
    private String emailPersona;
   
    private long telPersona;
    
    private String githubPersona;
    
    private String linkedinPersona;

    public Persona() {
    }

    public Persona(String nombrePersona, String puestoPersona, String ubicacionPersona, String sobreMi, String imgPersona, String emailPersona, long telPersona, String githubPersona, String linkedinPersona) {
        this.nombrePersona = nombrePersona;
        this.puestoPersona = puestoPersona;
        this.ubicacionPersona = ubicacionPersona;
        this.sobreMi = sobreMi;
        this.imgPersona = imgPersona;
        this.emailPersona = emailPersona;
        this.telPersona = telPersona;
        this.githubPersona = githubPersona;
        this.linkedinPersona = linkedinPersona;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getPuestoPersona() {
        return puestoPersona;
    }

    public void setPuestoPersona(String puestoPersona) {
        this.puestoPersona = puestoPersona;
    }

    public String getUbicacionPersona() {
        return ubicacionPersona;
    }

    public void setUbicacionPersona(String ubicacionPersona) {
        this.ubicacionPersona = ubicacionPersona;
    }

    public String getSobreMi() {
        return sobreMi;
    }

    public void setSobreMi(String sobreMi) {
        this.sobreMi = sobreMi;
    }

    public String getImgPersona() {
        return imgPersona;
    }

    public void setImgPersona(String imgPersona) {
        this.imgPersona = imgPersona;
    }

    public String getEmailPersona() {
        return emailPersona;
    }

    public void setEmailPersona(String emailPersona) {
        this.emailPersona = emailPersona;
    }

    public long getTelPersona() {
        return telPersona;
    }

    public void setTelPersona(long telPersona) {
        this.telPersona = telPersona;
    }

    public String getGithubPersona() {
        return githubPersona;
    }

    public void setGithubPersona(String githubPersona) {
        this.githubPersona = githubPersona;
    }

    public String getLinkedinPersona() {
        return linkedinPersona;
    }

    public void setLinkedinPersona(String linkedinPersona) {
        this.linkedinPersona = linkedinPersona;
    }
}
