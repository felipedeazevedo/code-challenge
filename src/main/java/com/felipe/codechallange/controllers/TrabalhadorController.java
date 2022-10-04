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

import com.felipe.codechallange.dtos.TrabalhadorDto;
import com.felipe.codechallange.models.Trabalhador;
import com.felipe.codechallange.services.TrabalhadorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Api(value = "Trabalhador")
@RequestMapping("/trabalhador")
public class TrabalhadorController {
	
	private final TrabalhadorService trabalhadorService;
	
	@ApiOperation(value = "Salva um novo trabalhador.")
	@PostMapping
	public ResponseEntity<Object> saveTrabalhador(@RequestBody @Valid TrabalhadorDto trabalhadorDto) {
        var trabalhadorModel = new Trabalhador();
        BeanUtils.copyProperties(trabalhadorDto, trabalhadorModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(trabalhadorService.save(trabalhadorModel));
	}
	
	@ApiOperation(value = "Busca todos os trabalhadores.")
	@GetMapping
	public ResponseEntity<Page<Trabalhador>> getAllTrabalhadores(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(trabalhadorService.findAll(pageable));
	}
	
	@ApiOperation(value = "Busca um trabalhador pelo id.")
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneTrabalhador(@PathVariable(value = "id") Integer id) {
		Optional<Trabalhador> trabalhadorOptional = trabalhadorService.findById(id);
        if (!trabalhadorOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabalhador não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(trabalhadorOptional.get());
	}
	
	@ApiOperation(value = "Deleta um trabalhador.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteTrabalhador(@PathVariable(value = "id") Integer id) {
		Optional<Trabalhador> trabalhadorOptional = trabalhadorService.findById(id);
		if (!trabalhadorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabalhador não encontrado.");
		}
		trabalhadorService.delete(trabalhadorOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Trabalhador deletado com sucesso.");
	}
	
	@ApiOperation(value = "Atualiza um trabalhador.")
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateTrabalhador(@PathVariable(value = "id") Integer id,
													@RequestBody @Valid TrabalhadorDto trabalhadorDto) {
		Optional<Trabalhador> trabalhadorOptional = trabalhadorService.findById(id);
		if (!trabalhadorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabalhador não encontrado.");
		}
		var trabalhadorModel = new Trabalhador();
		BeanUtils.copyProperties(trabalhadorDto, trabalhadorModel);
		trabalhadorModel.setId(trabalhadorOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(trabalhadorService.save(trabalhadorModel));
	}
	
}

