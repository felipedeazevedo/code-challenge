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

import com.felipe.codechallange.dtos.CargoDto;
import com.felipe.codechallange.entities.Cargo;
import com.felipe.codechallange.services.CargoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Api(value = "Cargo")
@RequestMapping("/cargo")
public class CargoController {
	
	private final CargoService cargoService;
	
	@ApiOperation(value = "Salva um novo cargo")
	@PostMapping
	public ResponseEntity<Object> saveCargo(@RequestBody @Valid CargoDto cargoDto) {
        var cargo = new Cargo();
        BeanUtils.copyProperties(cargoDto, cargo);
		return ResponseEntity.status(HttpStatus.CREATED).body(cargoService.save(cargo));
	}
	
	@ApiOperation(value = "Busca todos os cargos.")
	@GetMapping
	public ResponseEntity<Page<Cargo>> getAllCargoes(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(cargoService.findAll(pageable));
	}
	
	@ApiOperation(value = "Busca um cargo pelo id.")
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneCargo(@PathVariable(value = "id") Integer id) {
		Optional<Cargo> cargoOptional = cargoService.findById(id);
        if (!cargoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cargo não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cargoOptional.get());
	}
	
	@ApiOperation(value = "Deleta um cargo.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCargo(@PathVariable(value = "id") Integer id) {
		Optional<Cargo> cargoOptional = cargoService.findById(id);
		if (!cargoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cargo não encontrado.");
		}
		cargoService.delete(cargoOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Cargo deletado com sucesso.");
	}
	
	@ApiOperation(value = "Atualiza um cargo.")
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCargo(@PathVariable(value = "id") Integer id,
													@RequestBody @Valid CargoDto cargoDto) {
		Optional<Cargo> cargoOptional = cargoService.findById(id);
		if (!cargoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cargo não encontrado.");
		}
		var cargo = new Cargo();
		BeanUtils.copyProperties(cargoDto, cargo);
		cargo.setId(cargoOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(cargoService.save(cargo));
	}
	
}

