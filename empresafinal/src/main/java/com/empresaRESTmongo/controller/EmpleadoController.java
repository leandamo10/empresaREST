package com.empresaRESTmongo.controller;

import com.empresaRESTmongo.model.Empleado;
import com.empresaRESTmongo.service.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/api/empleados")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/{dni}")
    public ResponseEntity<Empleado> getEmpleado(@PathVariable String dni) {
        return empleadoService.findEmpleado(dni);
    }

    // Agregar query parameter sueldMin y sueldoMax y (si estan definidos) devolver filtrando correctamente (no filtrado en memoria sino filtrado en la propia query a mongo)
    @GetMapping("/sueldo")
    public List<Empleado> getEmpleados(@RequestParam Double sueldoMin, Double sueldoMax) {
        return (empleadoService.filterBySueldo(sueldoMin, sueldoMax));
    }

    @GetMapping("")
    public ResponseEntity getEmpleadosByParam(@RequestParam (required = false) Map<String, String> allParams){
        return empleadoService.findByParam(allParams);
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity deleteEmpleado(@PathVariable String dni) {
        return (empleadoService.borrar(dni));
    }
}

