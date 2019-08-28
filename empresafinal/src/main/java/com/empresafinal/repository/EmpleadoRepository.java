package com.empresafinal.repository;

import com.empresafinal.model.CargoEnum;
import com.empresafinal.model.Empleado;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class EmpleadoRepository {

    private Map<String, Empleado> listaEmpleados = new HashMap<>();

    public Empleado agregarEmpleado(Empleado empleado) {
        listaEmpleados.put(empleado.getDni(), empleado);
        return empleado;
    }

    public Collection<Empleado> listar() {
        return listaEmpleados.values();
    }

    public Collection<Empleado> listarPorCargo(CargoEnum cargoEnum) {
        return listaEmpleados.values().stream()
                .filter(empleado -> empleado.getCargo().equals(cargoEnum))
                .collect(Collectors.toList());
    }

    public Empleado buscarEmpleado(String dni) {
        return listaEmpleados.get(dni);
    }

    public Empleado modificar(String dni, Empleado empleado) {
        if (listaEmpleados.get(dni) == null) {
            return null;
        } else {
            return listaEmpleados.put(dni, empleado);
        }
    }

    public Empleado borrar(String dni) {
        return listaEmpleados.remove(dni);
    }
}
