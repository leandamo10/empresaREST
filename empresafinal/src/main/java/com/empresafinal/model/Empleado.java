package com.empresafinal.model;

public class Empleado {

    private String nombre;
    private String apellido;
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
