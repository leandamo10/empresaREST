package com.empresaRESTmongo.service;

import com.empresaRESTmongo.model.CargoEnum;
import com.empresaRESTmongo.model.Empleado;
import com.empresaRESTmongo.model.EmpleadoFacultad;
import com.empresaRESTmongo.model.JefeDeZona;
import com.empresaRESTmongo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JefeService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    EmpleadoService empleadoService;

    public ResponseEntity<JefeDeZona> newJefe(JefeDeZona jefeDeZona){
        if (empleadoRepository.findByDni(jefeDeZona.getDni()) != null){
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        } else if (jefeDeZona.getCargo().equals(CargoEnum.JEFE_DE_ZONA)){
        return new ResponseEntity<>(empleadoRepository.save(jefeDeZona), HttpStatus.CREATED);}
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<JefeDeZona> newJefeFromFacultad(String dni) {
        EmpleadoFacultad empleado = empleadoService.getEmpleadoFromFacultadByDni(dni);
        if (empleado==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (empleadoRepository.findByDni(empleado.getDni()) == null) {
                JefeDeZona jefeDeZona1 = new JefeDeZona();
                jefeDeZona1.setCargo(CargoEnum.JEFE_DE_ZONA);
                jefeDeZona1.setNombre(empleado.getNombre());
                jefeDeZona1.setApellido(empleado.getApellido());
                jefeDeZona1.setDni(empleado.getDni());
                empleadoRepository.save(jefeDeZona1);
                return new ResponseEntity<JefeDeZona>(jefeDeZona1, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
    }

    public ResponseEntity findJefes(CargoEnum Cargo){
        return new ResponseEntity(empleadoRepository.findByCargo(Cargo),HttpStatus.OK);
    }

    public ResponseEntity<JefeDeZona> insertJefe(String dni, JefeDeZona jefeDeZona) {
        if (jefeDeZona.getDni().equals(dni)) {
            if (empleadoRepository.findByDni(jefeDeZona.getDni()) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else if (empleadoRepository.findByDni(dni).getCargo().equals(CargoEnum.JEFE_DE_ZONA) && jefeDeZona.getCargo().equals(CargoEnum.JEFE_DE_ZONA)) {
                JefeDeZona jefeDeZona1 = (JefeDeZona) empleadoRepository.findByDni(dni);
                jefeDeZona1.setDespacho(jefeDeZona.getDespacho());
                jefeDeZona1.setSecretario(jefeDeZona.getSecretario());
                jefeDeZona1.setSecretarioDni(jefeDeZona1.getSecretarioDni());
                jefeDeZona1.setApellido(jefeDeZona.getApellido());
                jefeDeZona1.setCargo(jefeDeZona.getCargo());
                jefeDeZona1.setNombre(jefeDeZona.getNombre());
                jefeDeZona1.setSueldo(jefeDeZona.getSueldo());
                jefeDeZona1.setTelefono(jefeDeZona.getTelefono());
                return new ResponseEntity(empleadoRepository.save(jefeDeZona1), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

