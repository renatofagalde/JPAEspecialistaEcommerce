package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pagamento")
public abstract class Pagamento extends EntidadeBaseInteger{

    @OneToOne(optional = false) //https://www.algaworks.com/aulas/3233/para-o-que-serve-o-atributo-optional/
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento  status;

}
