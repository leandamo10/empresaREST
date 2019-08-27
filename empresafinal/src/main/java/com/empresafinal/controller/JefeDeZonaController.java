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
    public ResponseEntity<JefeDeZona> crearEmpleado(@RequestBody JefeDeZona jefeDeZona) {
        return jefeDeZonaService.crearJefeDeZona(jefeDeZona);
    }

    @GetMapping("/jefesdezona")
    public Collection<Empleado> mostrarJefesDeZona() {
        return empleadoService.listar(CargoEnum.JEFE_DE_ZONA);
    }

    @PutMapping("/jefesdezona/{dni}")
    public ResponseEntity<JefeDeZona> modificar(@PathVariable String dni, @RequestBody JefeDeZona jefeDeZona) {
        return jefeDeZonaService.modificarJefe(dni, jefeDeZona);
    }
}
