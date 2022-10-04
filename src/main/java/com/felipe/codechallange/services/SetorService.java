package com.felipe.codechallange.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.felipe.codechallange.models.Setor;
import com.felipe.codechallange.repositories.SetorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SetorService {
	
	private final SetorRepository repository;
	
	@Transactional
	public Setor save(Setor setor) {
		return repository.save(setor);
	}
	
	public Page<Setor> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public Optional<Setor> findById(Integer id) {
		return repository.findById(id);
	}
	
	@Transactional
	public void delete(Setor setor) {
		repository.delete(setor);
	}
	
	public Boolean existsByNome(String nome) {
		return repository.existsByNome(nome);
	}
}
