package com.empresafinal.controller;

import com.empresafinal.model.Empleado;
import com.empresafinal.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("v1/api")
public class EmpleadosController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/empleados/{dni}")
    public ResponseEntity<Empleado> mostrar(@PathVariable String dni) {
        Empleado empleado = empleadoService.mostrarEmpleado(dni);
        if (empleado == null) {
            return new ResponseEntity<Empleado>(empleado, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
        }
    }

    @GetMapping("/empleados")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Empleado> list() {
        return empleadoService.getEmpleados();
    }


    @DeleteMapping(value = "/empleados/{dni}")
    public ResponseEntity<Empleado> borrar(@PathVariable String dni) {
        Empleado empleado = empleadoService.mostrarEmpleado(dni);
        if (empleado == null) {
            return new ResponseEntity<>(empleado, HttpStatus.NOT_FOUND);
        } else {
            empleadoService.borrarEmpleado(dni);
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        }
    }

    @PutMapping("/empleados/{dni}")
    public ResponseEntity<Empleado> modificar(@PathVariable String dni, @RequestBody Empleado empleado) {
        Empleado empleado1 = empleadoService.mostrarEmpleado(dni);
        if (empleado1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!empleado.getDni().equals(empleado1.getDni())){
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        else {
            empleadoService.modificarEmpleado(dni, empleado);
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        }
    }
}


