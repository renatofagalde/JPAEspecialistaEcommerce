package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "pagamento_boleto")
@Entity
@Getter
@Setter
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PagamentoBoleto  extends EntidadeBaseInteger{

//	@EqualsAndHashCode.Include
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;

//	@Column(name = "pedido_id")
//	private Integer pedidoId;
//
//	@Enumerated(EnumType.STRING)
//	private StatusPagamento  status;

	@Column(name = "codigo_barras")
	private String codigoBarras;

}

