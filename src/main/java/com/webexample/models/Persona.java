/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webexample.models;

/**
 *
 * @author pideu
 */
public class Persona {
    private String nombr, apellido, sexo;

    public Persona(String nombr, String apellido, String sexo) {
        this.nombr = nombr;
        this.apellido = apellido;
        this.sexo = sexo;
    }

    public Persona() {
    }
    

    public String getNombr() {
        return nombr;
    }

    public void setNombr(String nombr) {
        this.nombr = nombr;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    
}
