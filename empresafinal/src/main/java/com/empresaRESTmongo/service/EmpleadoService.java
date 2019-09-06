package com.empresaRESTmongo.service;

import com.empresaRESTmongo.model.Empleado;
import com.empresaRESTmongo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public ResponseEntity<Empleado> findEmpleado(String dni) {
        Empleado empleado = empleadoRepository.findByDni(dni);
        if (empleado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        }
    }

    public List<Empleado> filterBySueldo(String sueldoMin, String sueldoMax) {
        Double var1 = Double.parseDouble(sueldoMin);
        Double var2 = Double.parseDouble(sueldoMax);

        return empleadoRepository.findUserBySueldoBetween(var1, var2);
    }

    public ResponseEntity<Empleado> borrar(String dni) {
        Empleado empleado = empleadoRepository.findByDni(dni);
        if (empleado == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(empleadoRepository.deleteByDni(dni), HttpStatus.OK);
        }
    }

    public ResponseEntity findByParam(Map<String, String> allParams) {
        if (allParams.isEmpty()) {
            return new ResponseEntity(empleadoRepository.findAll(), HttpStatus.OK);
        } else {
            List<Empleado> Lista1 = new ArrayList<>();
            for (String key : allParams.keySet()) {
                if( key.equals("comisiones")||key.equals("sueldo")){
                    String var2 = allParams.get(key);
                    Lista1.addAll(empleadoRepository.searchByNumber(key, (Double.valueOf(var2))));
                } else {
                String var1 = allParams.get(key);
                Lista1.addAll(empleadoRepository.searchByParam(key, var1));}
            }
            Map<String, Empleado> ListaSinRepetidos1 = new HashMap<>();
            for (Empleado empleado : Lista1) {
                ListaSinRepetidos1.put(empleado.getDni(), empleado);
            }
            return new ResponseEntity(ListaSinRepetidos1, HttpStatus.OK);
        }
    }
}


