package com.empresaRESTmongo.controller;

import com.empresaRESTmongo.model.Empleado;
import com.empresaRESTmongo.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/nombre")
    public List<Empleado> getEmpleadosByName(@RequestParam String nombre){
        return empleadoService.findByNombre(nombre);
    }


    @DeleteMapping("/{dni}")
    public ResponseEntity deleteEmpleado(@PathVariable String dni) {
        return (empleadoService.borrar(dni));
    }
}

