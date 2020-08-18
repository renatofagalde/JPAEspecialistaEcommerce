package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class ClienteTest extends EntityManagerTest {


	@Test
	public void atualizarObjeto() {


		Cliente cliente = this.entityManager.find(Cliente.class, 1);
//		cliente.setId(1);

		cliente.setNome("Exercício");


		this.entityManager.getTransaction().begin();
		entityManager.merge(cliente);

		entityManager.getTransaction().commit();


		entityManager.clear();

		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertEquals("Exercício", clienteVerificacao.getNome());


	}

	@Test
	public void inserirPrimeiroObjeto() {

		Cliente produto = new Cliente();
//		produto.setId(2);
		produto.setNome("Java");

		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Cliente produtoVerificacao = entityManager.find(Cliente.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
	}

}
