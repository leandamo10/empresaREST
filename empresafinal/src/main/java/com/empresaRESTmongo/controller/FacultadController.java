package com.empresaRESTmongo.controller;


import com.empresaRESTmongo.model.EmpleadoFacultad;
import com.empresaRESTmongo.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/facultad")
public class FacultadController {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("")
    public List<EmpleadoFacultad> getEmpleadosFromFactultad() {
        return empleadoService.getEmpleadosFromFacultad();
    }

    @GetMapping("/{dni}")
    public EmpleadoFacultad getEmpleadosFromFactultad(@PathVariable String dni) {
        return empleadoService.getEmpleadoFromFacultadByDni(dni);
    }
}
