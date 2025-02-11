package com.parkauto.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkauto.rest.entity.Camion;

@Repository
public interface ICamionRepository extends JpaRepository<Camion, Long> {

}
