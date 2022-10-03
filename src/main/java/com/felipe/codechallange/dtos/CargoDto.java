package com.felipe.codechallange.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.felipe.codechallange.entities.Cargo;
import com.felipe.codechallange.entities.Setor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoDto {
	
	@NotBlank
	private String nome;
	
	@NotNull
	private Setor setor;
	
	@NotNull
	private Cargo cargo;

}
