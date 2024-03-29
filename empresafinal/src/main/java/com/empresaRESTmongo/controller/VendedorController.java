package com.empresaRESTmongo.controller;

import com.empresaRESTmongo.model.CargoEnum;
import com.empresaRESTmongo.model.Vendedor;
import com.empresaRESTmongo.service.EmpleadoService;
import com.empresaRESTmongo.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/api/")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("empleados/vendedores")
    public ResponseEntity<Vendedor> newEmpleado(@Valid @RequestBody Vendedor vendedor) {
        return vendedorService.newVendedor(vendedor);
    }

    @GetMapping("empleados/vendedores")
    public ResponseEntity getVendedores() {
        return vendedorService.findVendedores(CargoEnum.VENDEDOR);
    }

    @PutMapping("empleados/vendedores/{dni}")
    public ResponseEntity<Vendedor> updateVendedor(@RequestBody Vendedor vendedor, @PathVariable String dni) {
        return vendedorService.insertVendedor(dni, vendedor);
    }

    @PostMapping("facultad/vendedores/{dni}")
    public ResponseEntity<Vendedor> newEmpleado(@PathVariable String dni) {
        return vendedorService.newVendedorFromFacultad(dni);
    }
}