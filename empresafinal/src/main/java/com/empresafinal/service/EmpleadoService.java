package com.empresafinal.service;

import com.empresafinal.model.*;
import com.empresafinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;


    public Collection<Empleado> getEmpleados() {
        return empleadoRepository.listar();
    }

    public Empleado mostrarEmpleado(String dni) {
        return empleadoRepository.mostrar(dni);
    }

    public Empleado borrarEmpleado(String dni) {
        return empleadoRepository.borrar(dni);
    }

    public Collection<Empleado> listar(CargoEnum cargoEnum) {
        return empleadoRepository.listarPorCargo(cargoEnum);
    }

    public Empleado modificarEmpleado(String dni, Empleado empleado) {
        return empleadoRepository.modificar(dni, empleado);
    }
}





