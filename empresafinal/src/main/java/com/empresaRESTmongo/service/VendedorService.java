package com.empresaRESTmongo.service;

import com.empresaRESTmongo.model.CargoEnum;
import com.empresaRESTmongo.model.EmpleadoFacultad;
import com.empresaRESTmongo.model.Vendedor;
import com.empresaRESTmongo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class VendedorService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    EmpleadoService empleadoService;

    public ResponseEntity<Vendedor> newVendedor(Vendedor vendedor) {
        if (empleadoRepository.findByDni(vendedor.getDni()) != null) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        } else if (vendedor.getCargo().equals(CargoEnum.VENDEDOR)) {
            return new ResponseEntity<>(empleadoRepository.save(vendedor), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity findVendedores(CargoEnum Cargo) {
        return new ResponseEntity(empleadoRepository.findByCargo(Cargo), HttpStatus.OK);
    }

    public ResponseEntity<Vendedor> insertVendedor(String dni, Vendedor vendedor) {
        if (vendedor.getDni().equals(dni)) {
            if (empleadoRepository.findByDni(dni) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else if (empleadoRepository.findByDni(dni).getCargo().equals(CargoEnum.VENDEDOR) && vendedor.getCargo().equals(CargoEnum.VENDEDOR)) {
                Vendedor vendedor1 = (Vendedor) empleadoRepository.findByDni(dni);
                vendedor1.setComisiones(vendedor.getComisiones());
                vendedor1.setApellido(vendedor.getApellido());
                vendedor1.setCargo(vendedor.getCargo());
                vendedor1.setNombre(vendedor.getNombre());
                vendedor1.setSueldo(vendedor.getSueldo());
                vendedor1.setTelefono(vendedor.getTelefono());
                return new ResponseEntity(empleadoRepository.save(vendedor1), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<Vendedor> newVendedorFromFacultad(String dni) {
        EmpleadoFacultad empleado = empleadoService.getEmpleadoFromFacultadByDni(dni);
        if (empleado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (empleadoRepository.findByDni(empleado.getDni()) == null) {
                Vendedor vendedor = new Vendedor();
                vendedor.setCargo(CargoEnum.VENDEDOR);
                vendedor.setNombre(empleado.getNombre());
                vendedor.setApellido(empleado.getApellido());
                vendedor.setDni(empleado.getDni());
                empleadoRepository.save(vendedor);
                return new ResponseEntity(vendedor, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
    }
}


