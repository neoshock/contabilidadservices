
package com.webexample.models;

public class Grupo {
    private int id;
    private String codigo, nombre;

    public Grupo() {
    }
    
    public Grupo(int id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Grupo{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + '}';
    }
    
    
}
