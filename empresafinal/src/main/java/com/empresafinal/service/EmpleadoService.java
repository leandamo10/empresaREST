package com.empresafinal.service;

import com.empresafinal.model.*;
import com.empresafinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;


    public Collection<Empleado> getEmpleados() {
        return empleadoRepository.listar();
    }

    public ResponseEntity<Empleado> buscar(String dni) {
        Empleado empleado = empleadoRepository.buscarEmpleado(dni);
        if (empleado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        }
    }
    public Empleado mostrarEmpleado(String dni){
        return empleadoRepository.buscarEmpleado(dni);
    }


    public ResponseEntity<Empleado> borrarEmpleado(String dni) {
        Empleado empleado = empleadoRepository.buscarEmpleado(dni);
        if (empleado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            empleadoRepository.borrar(dni);
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        }
    }

    public Collection<Empleado> listar(CargoEnum cargoEnum) {
        return empleadoRepository.listarPorCargo(cargoEnum);
    }
}





