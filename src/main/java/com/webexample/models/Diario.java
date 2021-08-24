package com.webexample.models;

import java.util.Date;

public class Diario {
    int idDiario;
    String nombre;
    String fechaApertura;
    String fechaCierre;
    String descripcion; 

    public Diario() {
    }

    public Diario(int idDiario, String nombre, String fechaApertura, String fechaCierre, String descripcion) {
        this.idDiario = idDiario;
        this.nombre = nombre;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.descripcion = descripcion;
    }

    public int getIdDiario() {
        return idDiario;
    }

    public void setIdDiario(int idDiario) {
        this.idDiario = idDiario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
