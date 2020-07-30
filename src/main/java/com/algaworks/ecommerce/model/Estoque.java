package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "estoque")
@Entity
@Getter
@Setter
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estoque  extends EntidadeBaseInteger{

//	@EqualsAndHashCode.Include
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;

/*
	@Column(name = "produto_id")
	private Integer produtoId;
*/

	private Integer quantidade;

	@OneToOne(optional = false)
	@JoinColumn(name = "produto_id")
	private Produto produto;



}
