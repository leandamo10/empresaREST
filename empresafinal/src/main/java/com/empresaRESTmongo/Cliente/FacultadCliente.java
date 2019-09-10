package com.empresaRESTmongo.Cliente;

import com.empresaRESTmongo.model.EmpleadoFacultad;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class FacultadCliente {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${facultad.host}")
    private String facultadHost;

    public List<EmpleadoFacultad> getEmpleadosFromFacultad() {
        try {
            ResponseEntity<String> body = restTemplate.getForEntity(facultadHost, String.class);
            return objectMapper.readValue(body.getBody(), new TypeReference<List<EmpleadoFacultad>>() {
            });
        } catch (Exception ex) {
        }
        return null;
    }

    public EmpleadoFacultad getEmpleadoFromFacultadByDni(String dni) {
        String url = facultadHost.concat(dni);
        try {
            ResponseEntity<String> xxx = restTemplate.getForEntity(url, String.class);
            return objectMapper.readValue(xxx.getBody(), new TypeReference<EmpleadoFacultad>() {
            });
        } catch (Exception ex) {
        }
        return null;
    }
}



