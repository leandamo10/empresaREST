package com.empresafinal.service;


import com.empresafinal.model.CargoEnum;
import com.empresafinal.model.JefeDeZona;
import com.empresafinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JefeDeZonaService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public JefeDeZona crearJefeDeZona(JefeDeZona jefeDeZona) {
        if (jefeDeZona.getCargo().equals(CargoEnum.JEFE_DE_ZONA)) {
            JefeDeZona jefeDeZona1= new JefeDeZona(jefeDeZona.getNombre(), jefeDeZona.getApellido(), jefeDeZona.getDni(),
                    jefeDeZona.getTelefono(), jefeDeZona.getSueldo(), jefeDeZona.getCargo());
            jefeDeZona1.setDespacho(jefeDeZona.getDespacho());
            jefeDeZona1.setSecretario(jefeDeZona.getSecretario());
            empleadoRepository.agregarEmpleado(jefeDeZona1);
            return jefeDeZona1;
        } else {
            return null;
        }
    }
}