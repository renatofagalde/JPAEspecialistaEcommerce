package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "pedido")
@Entity
@Getter
@Setter
@EntityListeners({GerarNotaFiscalListener.class, GenericoListener.class})
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido extends EntidadeBaseInteger {

//	@EqualsAndHashCode.Include
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false,foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    private Cliente cliente;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    //precision 19 = contando as casas decimais
    @Column(precision = 19, scale = 2)
    private BigDecimal total;

// alterado para  a classe abstrata na aula 6.16
    //https://www.algaworks.com/aulas/3229/mapeamento-relacionamentos-um-para-um-com-onetoone/
    //metadado para buscar os dados da relação
//	@OneToOne(mappedBy = "pedido")
//	private PagamentoCartao pagamentoCartao;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    public boolean isPago() {
        return StatusPedido.PAGO.equals(status);
    }

    //    @PrePersist
//    @PreUpdate
    public void calcularTotal() {
        if (itens != null) {
            total = itens.stream().map(ItemPedido::getPrecoProduto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    @PrePersist
    public void aoPersistir() {
        dataCriacao = LocalDateTime.now();
        calcularTotal();
    }

    @PreUpdate
    public void aoAtualizar() {
        dataUltimaAtualizacao = LocalDateTime.now();
        calcularTotal();
    }

    @PostPersist
    public void aposPersistir() {
        System.out.println("Após persistir Pedido.");
    }

    @PostUpdate
    public void aposAtualizar() {
        System.out.println("Após atualizar Pedido.");
    }

    @PreRemove
    public void aoRemover() {
        System.out.println("Antes de remover Pedido.");
    }

    @PostRemove
    public void aposRemover() {
        System.out.println("Após remover Pedido.");
    }

    @PostLoad
    public void aoCarregar() {
        System.out.println("Após carregar o Pedido.");
    }
}
