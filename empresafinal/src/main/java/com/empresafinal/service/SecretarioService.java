package com.empresafinal.service;

import com.empresafinal.model.CargoEnum;
import com.empresafinal.model.Empleado;
import com.empresafinal.model.JefeDeZona;
import com.empresafinal.model.Secretario;
import com.empresafinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SecretarioService {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private EmpleadoService empleadoService;

    public ResponseEntity<Secretario> crearSecretario(Secretario secretario) {
        if (empleadoService.mostrarEmpleado(secretario.getDni()) == null) {
            if (secretario.getCargo().equals(CargoEnum.SECRETARIO)) {
                Secretario secretario1 = new Secretario(secretario.getNombre(), secretario.getApellido(), secretario.getDni(),
                        secretario.getTelefono(), secretario.getSueldo(), secretario.getCargo(), secretario.getDespacho(),secretario.getFax());
                empleadoRepository.agregarEmpleado(secretario1);
                return new ResponseEntity<>(secretario, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Secretario> modificarSecretario(String dni, Secretario secretario) {
        if (empleadoRepository.mostrar(dni)==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!secretario.getDni().equals(dni)) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        } else {
            if (secretario.getCargo().equals(CargoEnum.SECRETARIO)) {
                empleadoRepository.modificar(dni, secretario );
                return new ResponseEntity<>(secretario, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
    }
}
