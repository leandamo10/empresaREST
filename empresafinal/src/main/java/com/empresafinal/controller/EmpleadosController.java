package com.empresafinal.controller;

import com.empresafinal.model.Empleado;
import com.empresafinal.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("v1/api/empleados")
public class EmpleadosController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/{dni}")
    public ResponseEntity<Empleado> mostrar(@PathVariable String dni) {
        return empleadoService.buscar(dni);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Empleado> list() {
        return empleadoService.getEmpleados();
    }


    @DeleteMapping("/{dni}")
    public ResponseEntity<Empleado> borrar(@PathVariable String dni) {
        return empleadoService.borrarEmpleado(dni);
    }
}


