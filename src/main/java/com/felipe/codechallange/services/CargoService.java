package com.felipe.codechallange.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.felipe.codechallange.entities.Cargo;
import com.felipe.codechallange.entities.Setor;
import com.felipe.codechallange.repositories.CargoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CargoService {
	
	private final CargoRepository repository;
	
	@Transactional
	public Cargo save(Cargo cargo) {
		return repository.save(cargo);
	}
	
	public Page<Cargo> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public Optional<Cargo> findById(Integer id) {
		return repository.findById(id);
	}
	
	public List<Cargo> findCargosByIdSetor(Setor setor) {
		return repository.findBySetor(setor);
	}
	
	@Transactional
	public void delete(Cargo cargo) {
		repository.delete(cargo);
	}
}