package com.empresafinal.service;

import com.empresafinal.model.CargoEnum;
import com.empresafinal.model.Secretario;
import com.empresafinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretarioService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Secretario crearSecretario(Secretario secretario) {
        if (secretario.getCargo().equals(CargoEnum.SECRETARIO)) {
            Secretario secretario1= new Secretario(secretario.getNombre(), secretario.getApellido(), secretario.getDni(),
                    secretario.getTelefono(), secretario.getSueldo(), secretario.getCargo());
            secretario1.setDespacho(secretario.getDespacho());
            secretario1.setFax(secretario.getFax());
            empleadoRepository.agregarEmpleado(secretario1);
            return secretario1;
        } else {
            return null;
        }
    }
}