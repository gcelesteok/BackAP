
package com.Proyecto.Portfolio.Dto;

import javax.validation.constraints.NotBlank;


public class DtoProyectos {
    
    @NotBlank
    private String nombre;
    @NotBlank
    private String imgProyecto;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String url;

    public DtoProyectos() {
    }

    public DtoProyectos(String nombre, String imgProyecto, String descripcion, String url) {
        this.nombre = nombre;
        this.imgProyecto = imgProyecto;
        this.descripcion = descripcion;
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImgProyecto() {
        return imgProyecto;
    }

    public void setImgProyecto(String imgProyecto) {
        this.imgProyecto = imgProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
     
}
