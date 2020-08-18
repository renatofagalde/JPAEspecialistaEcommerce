package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Table(name = "produto",
        uniqueConstraints = { @UniqueConstraint(name = "unq_nome", columnNames = { "nome" }) },
        indexes = { @Index(name = "idx_nome", columnList = "nome") })
@Entity
@Getter
@Setter
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto  extends EntidadeBaseInteger{

//	@EqualsAndHashCode.Include
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;

	@Column(name = "data_criacao", updatable = false)
	private LocalDateTime dataCriacao;

	@Column(name = "data_ultima_atualizacao", insertable = false)
	private LocalDateTime dataUltimaAtualizacao;

	@Column(length = 100)
	private String nome;

	@Column(columnDefinition = "varchar(275) not null default 'descricao'")
	private String descricao;

	//precision 19 = contando as casas decimais
	@Column(precision = 19,scale = 2)
	private BigDecimal preco;

	//dono do relacionamento
	//https://www.algaworks.com/aulas/3221/conhecendo-os-tipos-de-relacionamentos-entre-entidades/
	//quem tem o JoinColumn ou JoinTable é o dono do relacionamento
	//quem tem o mappedBy é o lado fraco da relacao

	//https://www.algaworks.com/aulas/3228/mapeando-relacionamentos-muitos-para-muitos-com-manytomany-e-jointable/
	@ManyToMany
	@JoinTable(name = "produto_categoria",
			   joinColumns = @JoinColumn(name = "produto_id"),
			   inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias;

	@OneToOne(mappedBy = "produto")
	private Estoque estoque;

	@ElementCollection
    //aula 6.9 mapeando colecoes de tipo básico
    @CollectionTable(name = "produto_tag",joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "tag") //nome da propriedade que guardará o valor deste atributo
	private List<String> tags;


    @ElementCollection
    //aula 6.9 mapeando colecoes de objetos
    @CollectionTable(name = "produto_atributo",joinColumns = @JoinColumn(name = "produto_id"))
    private List<Atributo> atributos;

    @Lob
    private byte[] foto;
}
