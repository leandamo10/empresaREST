package com.empresafinal.model;

public class JefeDeZona extends Empleado {

    private String despacho;
    private String secretario;

    public JefeDeZona(){}

    public JefeDeZona (String nombre, String apellido, String dni, String telefono, double sueldo, CargoEnum cargo) {
        super(nombre, apellido, dni, telefono, sueldo, cargo);
        this.despacho = despacho;
        this.secretario = secretario;
    }

    public String getDespacho() {
        return despacho;
    }

    public void setDespacho(String despacho) {
        this.despacho = despacho;
    }

    public String getSecretario() {
        return secretario;
    }

    public void setSecretario(String secretario) {
        this.secretario = secretario;
    }
}
