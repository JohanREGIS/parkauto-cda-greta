package com.parkauto.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkauto.rest.entity.Commande;

@Repository
public interface ICommandeRepository extends JpaRepository<Commande, Long> {

}
