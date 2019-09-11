package com.empresaRESTmongo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpleadoFacultad {

    private String nombre;
    private String apellido;
    private String dni;
    private CargoEnumFacultad cargo;

    public EmpleadoFacultad(String nombre, String apellido, String dni, CargoEnumFacultad cargo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.cargo = cargo;
    }

    public EmpleadoFacultad() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public CargoEnumFacultad getCargo() {
        return cargo;
    }

    public void setCargo(CargoEnumFacultad cargo) {
        this.cargo = cargo;
    }
}
