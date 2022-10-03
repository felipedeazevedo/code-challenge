package com.felipe.codechallange.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.codechallange.dtos.SetorDto;
import com.felipe.codechallange.entities.Setor;
import com.felipe.codechallange.services.SetorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Api(value = "Setor")
@RequestMapping("/setor")
public class SetorController {
	
	private final SetorService setorService;
	
	@ApiOperation(value = "Salva um novo setor")
	@PostMapping
	public ResponseEntity<Object> saveSetor(@RequestBody @Valid SetorDto setorDto) {
        if (setorService.existsByNome(setorDto.getNome())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Este nome já está vinculado a um setor.");
        }
        var setor = new Setor();
        BeanUtils.copyProperties(setorDto, setor);
		return ResponseEntity.status(HttpStatus.CREATED).body(setorService.save(setor));
	}
	
	@ApiOperation(value = "Busca todos os setores.")
	@GetMapping
	public ResponseEntity<Page<Setor>> getAllSetores(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(setorService.findAll(pageable));
	}
	
	@ApiOperation(value = "Busca um setor pelo id.")
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneSetor(@PathVariable(value = "id") Integer id) {
		Optional<Setor> setorOptional = setorService.findById(id);
        if (!setorOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Setor não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(setorOptional.get());
	}
	
	@ApiOperation(value = "Deleta um setor.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteSetor(@PathVariable(value = "id") Integer id) {
		Optional<Setor> setorOptional = setorService.findById(id);
		if (!setorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Setor não encontrado.");
		}
		setorService.delete(setorOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Setor deletado com sucesso.");
	}
	
	@ApiOperation(value = "Atualiza um setor.")
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateSetor(@PathVariable(value = "id") Integer id,
													@RequestBody @Valid SetorDto setorDto) {
		Optional<Setor> setorOptional = setorService.findById(id);
		if (!setorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Setor não encontrado.");
		}
		var setor = new Setor();
		BeanUtils.copyProperties(setorDto, setor);
		setor.setId(setorOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(setorService.save(setor));
	}
	
}
