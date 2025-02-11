package com.parkauto.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkauto.rest.entity.Voiture;

@Repository
public interface IVoitureRepository extends JpaRepository<Voiture, Long> {

}
