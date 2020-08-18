package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "estoque")
@Entity
@Getter
@Setter
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estoque extends EntidadeBaseInteger {

//	@EqualsAndHashCode.Include
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;

/*
	@Column(name = "produto_id")
	private Integer produtoId;
*/

    @Column(columnDefinition = "integer(11) not null")
    private Integer quantidade;

    @OneToOne(optional = false)
    @JoinColumn(name = "produto_id",foreignKey = @ForeignKey(name = "fk_estoque_produto_id"))
    private Produto produto;


}
