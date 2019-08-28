package com.empresafinal.service;

import com.empresafinal.model.CargoEnum;
import com.empresafinal.model.Vendedor;
import com.empresafinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VendedorService {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private EmpleadoService empleadoService;


    public ResponseEntity<Vendedor> crearVendedor(Vendedor vendedor) {
        if (empleadoService.mostrarEmpleado(vendedor.getDni()) == null) {
            if (vendedor.getCargo().equals(CargoEnum.VENDEDOR)) {
                Vendedor vendedor1 = new Vendedor(vendedor.getNombre(), vendedor.getApellido(), vendedor.getDni(),
                        vendedor.getTelefono(), vendedor.getSueldo(), vendedor.getCargo(), vendedor.getComisiones());
                empleadoRepository.agregarEmpleado(vendedor1);
                return new ResponseEntity<>(vendedor1, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Vendedor> modificarVendedor(String dni, Vendedor vendedor) {
        if (empleadoRepository.buscarEmpleado(dni)==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!vendedor.getDni().equals(dni)) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        } else {
            if (vendedor.getCargo().equals(CargoEnum.VENDEDOR)) {
                empleadoRepository.modificar(dni, vendedor );
                return new ResponseEntity<>(vendedor, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
    }
}
