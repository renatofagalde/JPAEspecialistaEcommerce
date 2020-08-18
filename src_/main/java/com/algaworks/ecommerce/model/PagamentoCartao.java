package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//@Table(name = "pagamento_cartao")
@Entity
@Getter
@Setter
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)

//aula 6.17
@DiscriminatorValue("cartao") //comentado por conta do TABLE_PER_CLASS
public class PagamentoCartao extends Pagamento {

//	@EqualsAndHashCode.Include
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;

    //não é obrigado a mapear o caminho de volta

//  aula https://www.algaworks.com/aulas/3257/entendendo-a-diferenca-entre-estender-uma-entidade-abstrata-e-usar-a-anotacao-mappedsuperclass
//  propriedades movidas para classe abstrata Pagamento

//	@OneToOne(optional = false) //https://www.algaworks.com/aulas/3233/para-o-que-serve-o-atributo-optional/
//	@JoinColumn(name = "pedido_id")
//	private Pedido pedido;
//
//	@Enumerated(EnumType.STRING)
//	private StatusPagamento  status;

    @Column(name = "numero_cartao")
    private String numeroCartao;

}
