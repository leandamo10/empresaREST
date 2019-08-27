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

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("v1/api/empleados/jefesdezona")
public class JefeDeZonaController {

    @Autowired
    private JefeDeZonaService jefeDeZonaService;

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("")
    public ResponseEntity<JefeDeZona> crearEmpleado(@Valid @RequestBody JefeDeZona jefeDeZona) {
        return jefeDeZonaService.crearJefeDeZona(jefeDeZona);
    }

    @GetMapping("")
    public Collection<Empleado> mostrarJefesDeZona() {
        return empleadoService.listar(CargoEnum.JEFE_DE_ZONA);
    }

    @PutMapping("/{dni}")
    public ResponseEntity<JefeDeZona> modificar(@PathVariable String dni, @RequestBody JefeDeZona jefeDeZona) {
        return jefeDeZonaService.modificarJefe(dni, jefeDeZona);
    }
}
