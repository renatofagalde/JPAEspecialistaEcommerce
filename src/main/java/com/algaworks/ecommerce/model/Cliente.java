package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Table(name = "cliente")
@Entity
@Getter
@Setter
// @EqualsAndHashCode(onlyExplicitlyIncluded = true) removido por conta da heranca
@SecondaryTable(name="cliente_detalhe",pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"))
public class Cliente extends EntidadeBaseInteger{

//    mapeado como superclass
//    @EqualsAndHashCode.Include
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    private String nome;

    //ordinal usa a posicao
    //string usa o valor
    @Enumerated(EnumType.STRING)
    @Column(table = "cliente_detalhe")
    private SexoCliente sexo;

    @Column(table = "cliente_detalhe", name = "data_nascimento")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    //@Transient aula 6.8
    @Transient
    private String primeiroNome;

    //6.11
    @ElementCollection
    @CollectionTable(name = "cliente_contato", joinColumns = @JoinColumn(name = "cliente_id"))
    @MapKeyColumn(name = "tipo")
    @Column(name = "descricao") //nome da coluna que irá representar o valor
    private Map<String, String> contatos;


    //quando carregar da base callback
    @PostLoad
    public void configurarPrimeiroNome() {

        //https://stackoverflow.com/questions/51299126/difference-between-isempty-and-isblank-method-in-java-11?noredirect=1&lq=1#:~:text=Java%2011%20%2D%20isBlank(),Character%23isWhitespace(int).

        if (nome != null && !nome.trim().isEmpty()) {
            int index = nome.indexOf(" ");
            if (index > -1) {
                this.primeiroNome = nome.substring(0, index);
            }
        }
    }

}
