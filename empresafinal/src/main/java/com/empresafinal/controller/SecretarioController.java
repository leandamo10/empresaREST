package com.empresafinal.controller;


import com.empresafinal.model.CargoEnum;
import com.empresafinal.model.Empleado;
import com.empresafinal.model.JefeDeZona;
import com.empresafinal.model.Secretario;
import com.empresafinal.service.EmpleadoService;
import com.empresafinal.service.SecretarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("v1/api/empleados/secretarios")
public class SecretarioController {

    @Autowired
    private SecretarioService secretarioService;
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("")
    public ResponseEntity<Secretario> crearEmpleado(@Valid @RequestBody Secretario secretario) {
        return secretarioService.crearSecretario(secretario);
    }

    @GetMapping("")
    public Collection<Empleado> mostrarSecretarios() {
        return empleadoService.listar(CargoEnum.SECRETARIO);
    }

    @PutMapping("/{dni}")
    public ResponseEntity<Secretario> modificar(@PathVariable String dni, @RequestBody Secretario secretario) {
        return secretarioService.modificarSecretario(dni, secretario);
    }
}
