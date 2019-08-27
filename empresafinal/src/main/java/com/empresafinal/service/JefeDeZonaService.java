package com.empresafinal.service;


import com.empresafinal.model.CargoEnum;
import com.empresafinal.model.JefeDeZona;
import com.empresafinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JefeDeZonaService {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private EmpleadoService empleadoService;

    public ResponseEntity<JefeDeZona> crearJefeDeZona(JefeDeZona jefeDeZona) {
        if (empleadoService.mostrarEmpleado(jefeDeZona.getDni()) == null) {
            if (jefeDeZona.getCargo().equals(CargoEnum.JEFE_DE_ZONA)) {
                JefeDeZona jefeDeZona1 = new JefeDeZona(jefeDeZona.getNombre(), jefeDeZona.getApellido(), jefeDeZona.getDni(),
                        jefeDeZona.getTelefono(), jefeDeZona.getSueldo(), jefeDeZona.getCargo(), jefeDeZona.getDespacho(), jefeDeZona.getSecretario());
                empleadoRepository.agregarEmpleado(jefeDeZona1);
                return new ResponseEntity<>(jefeDeZona, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<JefeDeZona> modificarJefe(String dni, JefeDeZona jefeDeZona) {
        if (empleadoRepository.buscarEmpleado(dni)==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!jefeDeZona.getDni().equals(dni)) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        } else {
            if (jefeDeZona.getCargo().equals(CargoEnum.JEFE_DE_ZONA)) {
                empleadoRepository.modificar(dni, jefeDeZona);
                return new ResponseEntity<>(jefeDeZona, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
    }
}
