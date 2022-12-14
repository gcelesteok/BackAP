
package com.Proyecto.Portfolio.Dto;

import javax.validation.constraints.NotBlank;


public class DtoEducacion {
    @NotBlank
    private String nombreEducacion;
    @NotBlank
    private String descripcionEducacion;
    @NotBlank
    private String institucion;
    @NotBlank
    private String imagenE;
    

    public DtoEducacion() {
    }

    public DtoEducacion(String nombreEducacion, String descripcionEducacion, String institucion, String imagenE) {
        this.nombreEducacion = nombreEducacion;
        this.descripcionEducacion = descripcionEducacion;
        this.institucion = institucion;
        this.imagenE = imagenE;
    }

    public String getNombreEducacion() {
        return nombreEducacion;
    }

    public void setNombreEducacion(String nombreEducacion) {
        this.nombreEducacion = nombreEducacion;
    }

    public String getDescripcionEducacion() {
        return descripcionEducacion;
    }

    public void setDescripcionEducacion(String descripcionEducacion) {
        this.descripcionEducacion = descripcionEducacion;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getImagenE() {
        return imagenE;
    }

    public void setImagenE(String imagenE) {
        this.imagenE = imagenE;
    }
    
    
}
