package com.empresafinal.controller;


import com.empresafinal.model.CargoEnum;
import com.empresafinal.model.Empleado;
import com.empresafinal.model.Secretario;
import com.empresafinal.service.EmpleadoService;
import com.empresafinal.service.SecretarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("v1/api/empleados")
public class SecretarioController {

    @Autowired
    private SecretarioService secretarioService;
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/secretarios")
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Secretario secretario) {
        if (empleadoService.mostrarEmpleado(secretario.getDni())== null) {
            secretario = secretarioService.crearSecretario(secretario);
            if (secretario == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(secretario, HttpStatus.CREATED);
            }
        } else {
            return new ResponseEntity<>(empleadoService.mostrarEmpleado(secretario.getDni()), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/secretarios")
    public Collection<Empleado> mostrarSecretarios(){
        return empleadoService.listar(CargoEnum.SECRETARIO);
    }

    @PutMapping("/secretarios/{dni}")
    public ResponseEntity<Empleado> modificar(@PathVariable String dni, @RequestBody Secretario secretario) {
        Empleado empleado1 = empleadoService.mostrarEmpleado(dni);
        if (empleado1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!secretario.getDni().equals(empleado1.getDni())){
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        } else {
            if (secretario.getCargo().equals(CargoEnum.SECRETARIO)) {
                empleadoService.modificarEmpleado(dni, secretario);
                return new ResponseEntity<>(secretario, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
    }
}