package com.empresaRESTmongo.repository;

import com.empresaRESTmongo.model.CargoEnum;
import com.empresaRESTmongo.model.Empleado;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends MongoRepository<Empleado, String>{


    Empleado findByDni(String dni);
    List<Empleado> findByCargo(CargoEnum cargo);
    Long deleteByDni(String dni);
    @Query("{ 'sueldo' : { $gt: ?0, $lt: ?1 } }")
    List<Empleado> findUserBySalarioBetween(Double salarioMin, Double salarioMax);
    }

