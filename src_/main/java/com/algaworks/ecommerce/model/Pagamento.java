package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
//@Table(name = "pagamento") comentado na aula 6.18
@Table(name = "pagamento")

//aula 6.17
//@DiscriminatorColumn(name = "dtype",discriminatorType = DiscriminatorType.STRING)
//@DiscriminatorColumn(name = "tipo_pagamento",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorColumn(name = "tipo_pagamento",discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // trocado na aula 6.19
//@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento extends EntidadeBaseInteger {


    @MapsId // ID da tabela, aula: 6.6
    @OneToOne(optional = false) //https://www.algaworks.com/aulas/3233/para-o-que-serve-o-atributo-optional/
    @JoinColumn(name = "pedido_id",nullable = false,foreignKey = @ForeignKey(name = "fk_pagamento_pedido"))
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento  status;

}
