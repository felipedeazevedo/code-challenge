package com.felipe.codechallange.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.felipe.codechallange.models.Cargo;
import com.felipe.codechallange.models.Setor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrabalhadorDto {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@Size(max = 11)
	private String cpf; 
	
	@NotNull
	private Setor setor;
	
	@NotNull
	private Cargo cargo;
}
