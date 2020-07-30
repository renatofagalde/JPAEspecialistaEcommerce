package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

public class ConsultandoRegistrosTest extends EntityManagerTest {

	@Test
	public void buscarPorIdentificador() {
		Produto produto = entityManager.find(Produto.class, 1);


		//getReference só executa a query quando alguma propriedade for chamada
		//Produto produto = entityManager.getReference(Produto.class, 1);


		Assert.assertNotNull("Objeto localizado", produto);
		Assert.assertEquals("Kindle", produto.getNome());
	}

	@Test
	public void atualizarReferencia() {
		Produto produto = this.entityManager.find(Produto.class, 1);
		produto.setNome("Microfone");

		entityManager.refresh(produto);

		Assert.assertEquals("Kindle", produto.getNome());
	}

}
