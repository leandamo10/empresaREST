package com.empresaRESTmongo.service;

import com.empresaRESTmongo.model.CargoEnum;
import com.empresaRESTmongo.model.EmpleadoFacultad;
import com.empresaRESTmongo.model.Secretario;
import com.empresaRESTmongo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SecretarioService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    EmpleadoService empleadoService;

    public ResponseEntity<Secretario> newSecretario(Secretario secretario) {
        if (empleadoRepository.findByDni(secretario.getDni()) != null) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        } else if (secretario.getCargo().equals(CargoEnum.SECRETARIO)) {
            return new ResponseEntity<>(empleadoRepository.save(secretario), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity findSecretarios(CargoEnum Cargo) {
        return new ResponseEntity(empleadoRepository.findByCargo(Cargo), HttpStatus.OK);
    }

    public ResponseEntity<Secretario> insertSecretario(String dni, Secretario secretario) {
        if (secretario.getDni().equals(dni)) {
            if (empleadoRepository.findByDni(secretario.getDni()) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else if (empleadoRepository.findByDni(dni).getCargo().equals(CargoEnum.SECRETARIO) && secretario.getCargo().equals(CargoEnum.SECRETARIO)) {
                Secretario secretario1 = (Secretario) empleadoRepository.findByDni(dni);
                secretario1.setDespacho(secretario.getDespacho());
                secretario1.setFax(secretario.getFax());
                secretario1.setApellido(secretario.getApellido());
                secretario1.setCargo(secretario.getCargo());
                secretario1.setNombre(secretario.getNombre());
                secretario1.setSueldo(secretario.getSueldo());
                secretario1.setTelefono(secretario.getTelefono());
                return new ResponseEntity(empleadoRepository.save(secretario1), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Secretario> newSecretarioFromFacultad(String dni) {
        EmpleadoFacultad empleado = empleadoService.getEmpleadoFromFacultadByDni(dni);
        if (empleado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (empleadoRepository.findByDni(empleado.getDni()) == null) {
                Secretario secretario = new Secretario();
                secretario.setCargo(CargoEnum.SECRETARIO);
                secretario.setNombre(empleado.getNombre());
                secretario.setApellido(empleado.getApellido());
                secretario.setDni(empleado.getDni());
                empleadoRepository.save(secretario);
                return new ResponseEntity(secretario, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
    }
}
