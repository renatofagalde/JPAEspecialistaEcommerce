package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Atributo {

    private String nome;
    private String valor;
}
