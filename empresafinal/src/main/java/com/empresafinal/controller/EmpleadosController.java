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
        return empleadoService.mostrar(dni);
    }

    @GetMapping("/empleados")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Empleado> list() {
        return empleadoService.getEmpleados();
    }


    @DeleteMapping(value = "/empleados/{dni}")
    public ResponseEntity<Empleado> borrar(@PathVariable String dni) {
        return empleadoService.borrarEmpleado(dni);
    }
}


