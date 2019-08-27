package com.empresafinal.controller;


import com.empresafinal.model.CargoEnum;
import com.empresafinal.model.Empleado;
import com.empresafinal.model.JefeDeZona;
import com.empresafinal.service.EmpleadoService;
import com.empresafinal.service.JefeDeZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("v1/api/empleados")
public class JefeDeZonaController {

    @Autowired
    private JefeDeZonaService jefeDeZonaService;

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/jefesdezona")
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody JefeDeZona jefeDeZona) {
        if (empleadoService.mostrarEmpleado(jefeDeZona.getDni())== null) {
            jefeDeZona = jefeDeZonaService.crearJefeDeZona(jefeDeZona);
            if (jefeDeZona == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(jefeDeZona, HttpStatus.CREATED);
            }
        } else {
            return new ResponseEntity<>(empleadoService.mostrarEmpleado(jefeDeZona.getDni()), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/jefesdezona")
    public Collection<Empleado> mostrarJefesDeZona(){
        return empleadoService.listar(CargoEnum.JEFE_DE_ZONA);
    }

    @PutMapping("/jefesdezona/{dni}")
    public ResponseEntity<Empleado> modificar(@PathVariable String dni, @RequestBody JefeDeZona jefeDeZona) {
        Empleado empleado1 = empleadoService.mostrarEmpleado(dni);
        if (empleado1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!jefeDeZona.getDni().equals(empleado1.getDni())){
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        } else {
            if (jefeDeZona.getCargo().equals(CargoEnum.JEFE_DE_ZONA)) {
                empleadoService.modificarEmpleado(dni, jefeDeZona);
                return new ResponseEntity<>(jefeDeZona, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
    }
}