package com.parkauto.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkauto.rest.entity.Vehicule;

@Repository
public interface IVehiculeRepository extends JpaRepository<Vehicule, Long> {

}
