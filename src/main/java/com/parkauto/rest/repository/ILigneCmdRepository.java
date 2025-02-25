package com.parkauto.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkauto.rest.entity.LigneCmd;

@Repository
public interface ILigneCmdRepository extends JpaRepository<LigneCmd, Long> {

}
