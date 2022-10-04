package com.felipe.codechallange.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.felipe.codechallange.models.Trabalhador;
import com.felipe.codechallange.repositories.TrabalhadorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrabalhadorService {
	
	private final TrabalhadorRepository repository;
	
	@Transactional
	public Trabalhador save(Trabalhador trabalhador) {
		return repository.save(trabalhador);
	}
	
	public Page<Trabalhador> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public Optional<Trabalhador> findById(Integer id) {
		return repository.findById(id);
	}
	
	@Transactional
	public void delete(Trabalhador trabalhador) {
		repository.delete(trabalhador);
	}
}
