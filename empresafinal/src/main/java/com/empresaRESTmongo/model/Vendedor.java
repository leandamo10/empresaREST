package com.empresaRESTmongo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vendedor extends Empleado {

    private double comisiones;

    public Vendedor() {
    }

    public Vendedor(String id, String nombre, String apellido, String dni, String telefono, Double sueldo, CargoEnum cargo, double comisiones) {
        super(id, nombre, apellido, dni, telefono, sueldo, cargo);
        this.comisiones = comisiones;
    }

    public double getComisiones() {
        return comisiones;
    }

    public void setComisiones(double comisiones) {
        this.comisiones = comisiones;
    }
}
