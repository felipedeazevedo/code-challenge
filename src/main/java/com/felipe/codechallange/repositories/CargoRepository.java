package com.felipe.codechallange.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.codechallange.entities.Cargo;
import com.felipe.codechallange.entities.Setor;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
	
	List<Cargo> findBySetor(Setor setor);

}
