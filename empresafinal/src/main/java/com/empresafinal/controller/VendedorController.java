package com.empresafinal.controller;

import com.empresafinal.model.CargoEnum;
import com.empresafinal.model.Empleado;
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
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Vendedor vendedor) {
        if (empleadoService.mostrarEmpleado(vendedor.getDni()) == null) {
            vendedor = vendedorService.crearVendedor(vendedor);
            if (vendedor == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(vendedor, HttpStatus.CREATED);
            }
        } else {
            return new ResponseEntity<>(empleadoService.mostrarEmpleado(vendedor.getDni()), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/vendedores")
    public Collection<Empleado> mostrarVendedores(){
        return empleadoService.listar(CargoEnum.VENDEDOR);
    }

    @PutMapping("/vendedores/{dni}")
    public ResponseEntity<Empleado> modificar(@PathVariable String dni, @RequestBody Vendedor vendedor) {
        Empleado empleado1 = empleadoService.mostrarEmpleado(dni);
        if (empleado1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!vendedor.getDni().equals(empleado1.getDni())){
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        } else {
            if (vendedor.getCargo().equals(CargoEnum.VENDEDOR)) {
                empleadoService.modificarEmpleado(dni, vendedor);
                return new ResponseEntity<>(vendedor, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
    }
}



