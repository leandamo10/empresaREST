package com.empresaRESTmongo.service;

import com.empresaRESTmongo.model.Empleado;
import com.empresaRESTmongo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public ResponseEntity<Empleado> findEmpleado(String dni) {
        Empleado empleado = empleadoRepository.findByDni(dni);
        if (empleado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        }
    }

    public List<Empleado> findEmpleados() {
        return empleadoRepository.findAll();
    }

    public ResponseEntity<Empleado> borrar(String dni) {
        Empleado empleado = empleadoRepository.findByDni(dni);
        if (empleado == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity( empleadoRepository.deleteByDni(dni), HttpStatus.OK);
        }
    }

}


