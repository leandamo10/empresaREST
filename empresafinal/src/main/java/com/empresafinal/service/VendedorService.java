package com.empresafinal.service;


import com.empresafinal.model.CargoEnum;
import com.empresafinal.model.Empleado;
import com.empresafinal.model.Vendedor;
import com.empresafinal.repository.EmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class VendedorService {

    @Autowired
    private EmpleadoRepository  empleadoRepository;


    public VendedorService() {
    }

    public Vendedor crearVendedor(Vendedor vendedor) {
        if (vendedor.getCargo().equals(CargoEnum.VENDEDOR)) {
            Vendedor vendedor1 = new Vendedor(vendedor.getNombre(), vendedor.getApellido(), vendedor.getDni(),
                    vendedor.getTelefono(), vendedor.getSueldo(), vendedor.getCargo());
            vendedor1.setComisiones(vendedor.getComisiones());
            empleadoRepository.agregarEmpleado(vendedor1);
            return vendedor1;
        } else {
            return null;
        }
    }

}