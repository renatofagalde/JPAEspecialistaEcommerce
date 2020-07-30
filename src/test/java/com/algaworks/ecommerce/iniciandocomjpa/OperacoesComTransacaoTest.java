package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {

	@Test
	public void mostrarDiferencaPersistMerge() {

		Produto produtoPersist = new Produto();
//		produtoPersist.setId(5);
		produtoPersist.setNome("Smartphone one plus");
		produtoPersist.setDescricao("O processador mais rápido");
		produtoPersist.setPreco(new BigDecimal(2000));

		entityManager.getTransaction().begin();
		entityManager.persist(produtoPersist);
		produtoPersist.setNome("Smartphone two plus");
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto produtoVerificacao = entityManager.find(Produto.class, produtoPersist.getId());
		Assert.assertNotNull(produtoVerificacao);
	}

	@Test
	public void inserirObjetoComMerge() {

		Produto produto = new Produto();
//		produto.setId(4);
		produto.setNome("Microfone Rode Videmic");
		produto.setDescricao("A melhor qualidade de som");
		produto.setPreco(new BigDecimal(1000));

		entityManager.getTransaction().begin();
		final Produto merge = entityManager.merge(produto);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto produtoVerificacao = entityManager.find(Produto.class, merge.getId());
		Assert.assertNotNull(produtoVerificacao);
	}


	@Test
	public void atualizarObjetoGerenciado() {


		Produto produto = this.entityManager.find(Produto.class, 1);
//		produto.setId(1);

		produto.setNome("Kindle x");


		this.entityManager.getTransaction().begin();
		entityManager.merge(produto);

		entityManager.getTransaction().commit();


		entityManager.clear();

		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertEquals("Kindle x", produtoVerificacao.getNome());


	}

	@Test
	public void atualizarObjeto() {


		Produto produto = new Produto();
//		produto.setId(1);

		produto.setNome("Kindle paperwhite");
		produto.setDescricao("Conheça o novo kindle");
		produto.setPreco(new BigDecimal(599));


		this.entityManager.getTransaction().begin();
		produto = entityManager.merge(produto);

		entityManager.getTransaction().commit();


		entityManager.clear();

		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertEquals("Kindle paperwhite", produtoVerificacao.getNome());


	}

	@Test
	public void removerObjeto() {

		Produto produto = entityManager.find(Produto.class, 3);

		entityManager.getTransaction().begin();
		entityManager.remove(produto);
		entityManager.getTransaction().commit();


	}

	@Test
	public void inserirPrimeiroObjeto() {

		Produto produto = new Produto();
//		produto.setId(2);
		produto.setNome("Camera Canon");
		produto.setDescricao("A melhor definição para suas fotos");
		produto.setPreco(new BigDecimal(5000));

		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
	}

	@Test
	public void abrirEFecharATransacao() {
//        Produto produto = new Produto(); // Somente para o método não mostrar erros.

		entityManager.getTransaction().begin();

//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);

		entityManager.getTransaction().commit();
	}

}
