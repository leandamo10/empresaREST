package com.empresaRESTmongo.repository;

import com.empresaRESTmongo.model.CargoEnum;
import com.empresaRESTmongo.model.Empleado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends MongoRepository<Empleado, String>{


    Empleado findByDni(String dni);
    List<Empleado> findByCargo(CargoEnum cargo);
    Long deleteByDni(String dni);
}
