package com.felipe.codechallange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipe.codechallange.models.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer> {

	Boolean existsByNome(String nome);
}
