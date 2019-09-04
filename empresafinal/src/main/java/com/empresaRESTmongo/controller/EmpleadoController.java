package com.empresaRESTmongo.controller;


import com.empresaRESTmongo.model.Empleado;
import com.empresaRESTmongo.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("v1/api/empleados")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/{dni}")
    public ResponseEntity<Empleado> getEmpleado(@PathVariable String dni) {
        return empleadoService.findEmpleado(dni);
    }

    // Agregar query parameter sueldMin y sueldoMax y (si estan definidos) devolver filtrando correctamente (no filtrado en memorio sino filtrado en la propia query a mongo)
    @GetMapping("")
    public List<Empleado> getEmpleados(@RequestParam (required = false) Double sueldoMin, Double sueldoMax) {
        return (empleadoService.findEmpleados(sueldoMin, sueldoMax));
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity deleteEmpleado(@PathVariable String dni) {
        return (empleadoService.borrar(dni));
    }
}

