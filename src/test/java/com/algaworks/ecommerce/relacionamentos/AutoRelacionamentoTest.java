package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.Assert;
import org.junit.Test;

public class AutoRelacionamentoTest extends EntityManagerTest {

	@Test
	public void verificarRelacionamento() {

		Categoria categoriaPai = new Categoria();
		categoriaPai.setNome("Eletronicos");

		Categoria categoriaFilha= new Categoria();
		categoriaFilha.setNome("Celulares");

		categoriaFilha.setCategoriaPai(categoriaPai);


		this.entityManager.getTransaction().begin();

		this.entityManager.persist(categoriaPai);
		this.entityManager.persist(categoriaFilha);
		this.entityManager.getTransaction().commit();

		this.entityManager.clear();

		final Categoria verificacao = this.entityManager.find(Categoria.class, categoriaFilha.getId());
		Assert.assertNotNull(verificacao.getCategoriaPai());

		final Categoria verificacaoPai = this.entityManager.find(Categoria.class, categoriaPai.getId());

		Assert.assertFalse(verificacaoPai.getCategorias().isEmpty());

	}

}
