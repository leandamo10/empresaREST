package com.empresafinal.controller;

import com.empresafinal.model.CargoEnum;
import com.empresafinal.model.Empleado;
import com.empresafinal.model.Secretario;
import com.empresafinal.model.Vendedor;
import com.empresafinal.service.EmpleadoService;
import com.empresafinal.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("v1/api/empleados")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/vendedores")
    public ResponseEntity<Vendedor> crearEmpleado(@RequestBody Vendedor vendedor) {
        return vendedorService.crearVendedor(vendedor);
    }

    @GetMapping("/vendedores")
    public Collection<Empleado> mostrarVendedores() {
        return empleadoService.listar(CargoEnum.VENDEDOR);
    }

    @PutMapping("/vendedores/{dni}")
    public ResponseEntity<Vendedor> modificar(@PathVariable String dni, @RequestBody Vendedor vendedor) {
        return vendedorService.modificarVendedor(dni, vendedor);
    }
}



