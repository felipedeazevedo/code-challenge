package com.felipe.codechallange.dtos;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.felipe.codechallange.models.Cargo;
import com.felipe.codechallange.models.Trabalhador;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetorDto {
	
	@NotBlank
	private String nome;
	
	private List<@NotNull Cargo> cargos;
	
	private List<@NotNull Trabalhador> trabalhadores;
}
