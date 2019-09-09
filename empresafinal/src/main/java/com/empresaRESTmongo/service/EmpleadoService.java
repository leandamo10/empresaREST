package com.empresaRESTmongo.service;

import com.empresaRESTmongo.model.Empleado;
import com.empresaRESTmongo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


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

    public List<Empleado> filterBySueldo(Double sueldoMin, Double sueldoMax) {
        return empleadoRepository.findUserBySueldoBetween(sueldoMin, sueldoMax);
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
            List<Empleado> lista = new ArrayList<>();
            List<Empleado> lista1 = new ArrayList<>();
            for (String key : allParams.keySet()) {
                if (lista.isEmpty()) {
                    if (key.equals("comisiones") || key.equals("sueldo")) {
                        String var2 = allParams.get(key);
                        lista.addAll(empleadoRepository.searchByNumber(key, (Double.valueOf(var2))));
                        if (lista.isEmpty()) {
                            return new ResponseEntity(HttpStatus.OK);
                        }
                    } else {
                        String var1 = allParams.get(key);
                        lista.addAll(empleadoRepository.searchByParam(key, var1));
                        if (lista.isEmpty()) {
                            return new ResponseEntity(HttpStatus.OK);
                        }
                    }
                } else {
                    List<Empleado> lista2 = new ArrayList<>();
                    if (key.equals("comisiones") || key.equals("sueldo")) {
                        String var2 = allParams.get(key);
                        lista1.addAll(empleadoRepository.searchByNumber(key, (Double.valueOf(var2))));
                        if (lista1.isEmpty()) {
                            return new ResponseEntity(HttpStatus.OK);
                        }
                    } else {
                        String var1 = allParams.get(key);
                        lista1.addAll(empleadoRepository.searchByParam(key, var1));
                        if (lista1.isEmpty()) {
                            return new ResponseEntity(HttpStatus.OK);
                        }
                    }
                    for (Empleado empleado : lista) {
                        for (int i = 0; i < lista1.size(); i++) {
                            if (lista1.get(i).getDni().contains(empleado.getDni())) {
                                lista2.add(empleado);
                            }
                        }
                    }
                    lista = lista2;
                    lista1.removeAll(lista1);
                }

            }
            Set<Empleado> lista3 = lista.stream().collect(Collectors.toSet());
            return new ResponseEntity(lista3, HttpStatus.OK);
        }
    }
}


