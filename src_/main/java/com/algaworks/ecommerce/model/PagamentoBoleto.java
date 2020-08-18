package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//@Table(name = "pagamento_boleto")
@Entity
@Getter
@Setter
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)

//aula 6.17
@DiscriminatorValue("boleto") //comentado por conta do TABLE_PER_CLASS
public class PagamentoBoleto  extends Pagamento{

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

