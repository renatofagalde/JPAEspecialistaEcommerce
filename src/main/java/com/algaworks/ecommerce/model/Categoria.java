package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "categoria")
@Entity
@Getter
@Setter
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria  extends EntidadeBaseInteger{

//	@EqualsAndHashCode.Include
//	@Id

	//sequence aula 3.7

	//sequence aula 3.8
	//https://www.algaworks.com/aulas/3217/configurando-a-geracao-de-identificador-com-sequencegenerator/
//	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequencia")
//	@SequenceGenerator(name = "sequencia",sequenceName = "sequencia_chave_primaria")


	//table 3.9
	//https://www.algaworks.com/aulas/3218/configurando-a-geracao-de-identificador-com-tablegenerator/
/*
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tabela")
	@TableGenerator(name = "tabela",table = "hibernate_sequences",
					pkColumnName = "sequence_name",
					pkColumnValue = "categoria",
					valueColumnName = "next_val",
					initialValue = 100,
					allocationSize = 10)
*/

//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;

	private String nome;

/*
	@Column(name = "categoria_pai_id")
	private Integer categoriaPaiId;
*/

	@ManyToOne
	@JoinColumn(name = "categoria_pai_id")
	private Categoria categoriaPai;


	@OneToMany(mappedBy = "categoriaPai")
	private List<Categoria> categorias;

	@ManyToMany(mappedBy = "categorias")
	private List<Produto> produtos;


}
