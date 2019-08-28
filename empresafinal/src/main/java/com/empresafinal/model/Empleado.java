package com.empresafinal.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Empleado {

    @Pattern(regexp = "^[\\p{L}]+")
    private String nombre;
    @Pattern(regexp = "^[\\p{L}]+")
    private String apellido;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^(\\d{7}|\\d{8})$",message = "El dni tiene que tener 7 u 8 numeros")
    private String dni;
    private String telefono;
    private double sueldo;
    private CargoEnum Cargo;


    public Empleado() {
    }

    public Empleado(String nombre, String apellido, String dni, String telefono, double sueldo, CargoEnum cargo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.sueldo = sueldo;
        this.Cargo = cargo;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public CargoEnum getCargo() {
        return Cargo;
    }

    public void setCargo(CargoEnum cargo) {
        this.Cargo = cargo;
    }
}
