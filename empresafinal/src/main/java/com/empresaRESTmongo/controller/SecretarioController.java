package com.empresaRESTmongo.controller;

import com.empresaRESTmongo.model.CargoEnum;
import com.empresaRESTmongo.model.Secretario;
import com.empresaRESTmongo.service.EmpleadoService;
import com.empresaRESTmongo.service.SecretarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/api/empleados/secretarios")
public class SecretarioController {
    @Autowired
    private SecretarioService secretarioService;
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("")
    public ResponseEntity<Secretario> newEmpleado(@Valid @RequestBody Secretario sec) {
        return secretarioService.newSecretario(sec);
    }

    @GetMapping("")
    public ResponseEntity getVendedores(){
        return secretarioService.findSecretarios(CargoEnum.SECRETARIO);
    }

    @PutMapping("/{dni}")
    public ResponseEntity<Secretario> updateSecretario(@RequestBody Secretario secretario,@PathVariable String dni){
        return secretarioService.insertSecretario(dni, secretario);
    }

}