package com.empresaRESTmongo.controller;

import com.empresaRESTmongo.model.CargoEnum;
import com.empresaRESTmongo.model.JefeDeZona;
import com.empresaRESTmongo.service.EmpleadoService;
import com.empresaRESTmongo.service.JefeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/api/")
public class JefeDeZonaController {
    @Autowired
    private JefeService jefeService;
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("empleados/jefes")
    public ResponseEntity<JefeDeZona> newEmpleado(@Valid @RequestBody JefeDeZona jefeDeZona) {
        return jefeService.newJefe(jefeDeZona);
    }

    @GetMapping("empleados/jefes")
    public ResponseEntity getVendedores() {
        return jefeService.findJefes(CargoEnum.JEFE_DE_ZONA);
    }

    @PutMapping("empleados/jefes/{dni}")
    public ResponseEntity<JefeDeZona> updateSecretario(@RequestBody JefeDeZona jefeDeZona, @PathVariable String dni) {
        return jefeService.insertJefe(dni, jefeDeZona);
    }

    @PostMapping("facultad/jefes/{dni}")
    public ResponseEntity<JefeDeZona> newEmpleado(@PathVariable String dni) {
        return jefeService.newJefeFromFacultad(dni);
    }
}