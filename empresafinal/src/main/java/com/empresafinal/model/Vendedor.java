package com.empresafinal.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vendedor extends Empleado {

    private double comisiones;

    public Vendedor() {
    }

    public Vendedor(String nombre, String apellido, String dni, String telefono, double sueldo, CargoEnum cargo, double comisiones) {
        super(nombre, apellido, dni, telefono, sueldo, cargo);
        this.comisiones = comisiones;
    }

    public double getComisiones() {
        return comisiones;
    }

    public void setComisiones(double comisiones) {
        this.comisiones = comisiones;
    }
}
