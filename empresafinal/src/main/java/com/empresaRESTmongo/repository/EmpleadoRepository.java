package com.empresaRESTmongo.repository;

import com.empresaRESTmongo.model.CargoEnum;
import com.empresaRESTmongo.model.Empleado;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends MongoRepository<Empleado, String> {


    Empleado findByDni(String dni);

    List<Empleado> findByCargo(CargoEnum cargo);

    Long deleteByDni(String dni);

    @Query("{ 'nombre' : { $eq: ?0} }")
    List<Empleado> findByName(String nombre);

    @Query("{ 'sueldo' : { $gte: ?0, $lte: ?1} }")
    List<Empleado> findUserBySalarioBetween(Double salarioMin, Double salarioMax);

}

