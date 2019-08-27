package com.empresafinal.model;

import com.empresafinal.repository.EmpleadoRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JefeDeZona extends Empleado {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    private String despacho;
    private String secretarioDni;
    private Empleado secretario;

    public JefeDeZona() {
    }

    public JefeDeZona(String nombre, String apellido, String dni, String telefono, double sueldo, CargoEnum cargo, String despacho) {
        super(nombre, apellido, dni, telefono, sueldo, cargo);
        this.despacho = despacho;
    }

    public String getDespacho() {
        return despacho;
    }

    public void setDespacho(String despacho) {
        this.despacho = despacho;
    }

    public Empleado getSecretario() {
        return secretario;
    }

    public void setSecretario(Empleado empleado) {
        this.secretario = empleado;
    }

    public String getSecretarioDni() {
        return secretarioDni;
    }

    public void setSecretarioDni(String secretarioDni) {
        this.secretarioDni = secretarioDni;
    }

}
