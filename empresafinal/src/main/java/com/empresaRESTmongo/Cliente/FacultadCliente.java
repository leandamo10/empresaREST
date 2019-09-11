package com.empresaRESTmongo.Cliente;

import com.empresaRESTmongo.model.EmpleadoFacultad;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class FacultadCliente {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${facultad.host}")
    private String facultadHost;

    public ResponseEntity findEmpleadosFromFacultad() {
        try {
            ResponseEntity<String> body = restTemplate.getForEntity(facultadHost, String.class);
            List<EmpleadoFacultad> a = objectMapper.readValue(body.getBody(), new TypeReference<List<EmpleadoFacultad>>() {
            });
            return new ResponseEntity(a, HttpStatus.CREATED);
        } catch (ResourceAccessException ex) {
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity findEmpleadosFromFacultadByParams(Map<String, String> allParams) {
        try {
            List<EmpleadoFacultad> a = new ArrayList<>();
            for (String key : allParams.keySet()) {
                String var = allParams.get(key);
                String url = facultadHost + "?" + key + "=" + var;
                ResponseEntity<String> xxx = restTemplate.getForEntity(url, String.class);
                a = objectMapper.readValue(xxx.getBody(), new TypeReference<List<EmpleadoFacultad>>() {
                });

            }
            return new ResponseEntity(a, HttpStatus.OK);
        } catch (ResourceAccessException ex) {
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public EmpleadoFacultad getEmpleadoFromFacultadByDni(String dni) {
        String url = facultadHost.concat(dni);
        try {
            ResponseEntity<String> xxx = restTemplate.getForEntity(url, String.class);
            return objectMapper.readValue(xxx.getBody(), new TypeReference<EmpleadoFacultad>() {
            });
        } catch (Exception ex) {
            return null;
        }
    }

    public ResponseEntity<EmpleadoFacultad> findEmpleadoFromFacultadByDni(String dni) {
        String url = facultadHost.concat(dni);
        try {
            ResponseEntity<String> body = restTemplate.getForEntity(url, String.class);
            EmpleadoFacultad empleadoFacultad = objectMapper.readValue(body.getBody(), new TypeReference<EmpleadoFacultad>() {
            });
            return new ResponseEntity(empleadoFacultad, HttpStatus.OK);
        } catch (ResourceAccessException ex) {
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}



