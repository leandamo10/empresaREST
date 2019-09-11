package com.empresaRESTmongo.controller;

import com.empresaRESTmongo.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("v1/api/facultad")
public class FacultadController {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("")
    public ResponseEntity getEmpleadosFromFactultad(@RequestParam(required = false) Map<String, String> allParams) {
        return empleadoService.getEmpleadosFromFacultad(allParams);
    }

    @GetMapping("/{dni}")
    public ResponseEntity getEmpleadosFromFactultadbyDni(@PathVariable String dni) {
        return empleadoService.findEmpleadoFromFacultadByDni(dni);
    }
}
