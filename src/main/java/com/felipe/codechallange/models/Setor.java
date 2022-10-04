package com.felipe.codechallange.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_setor")
public class Setor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "setor", cascade = CascadeType.MERGE)
	private List<Cargo> cargos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "setor", cascade = CascadeType.MERGE)
	private List<Trabalhador> trabalhadores;
}
