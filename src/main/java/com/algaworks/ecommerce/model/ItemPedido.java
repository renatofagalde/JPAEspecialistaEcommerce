package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@IdClass(ItemPedidoId.class)
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

//	@EqualsAndHashCode.Include
//	@Id
//	@Column(name = "pedido_id")
//	private Integer pedidoId;

	@EmbeddedId
	private ItemPedidoId id;

	@ManyToOne(optional = false)
	@MapsId("pedidoId")
    //removido na aula 6.6
//	@JoinColumn(name = "pedido_id")
	@JoinColumn(name = "pedido_id", insertable = false, updatable = false)
	private Pedido pedido;

	@MapsId("produtoId")
	@ManyToOne(optional = false)
    //removido na aula 6.6
//	@JoinColumn(name = "produto_id")
	@JoinColumn(name = "produto_id", insertable = false, updatable = false)
	private Produto produto;

	@Column(name = "preco_produto")
	private BigDecimal precoProduto;

	private Integer quantidade;

}
