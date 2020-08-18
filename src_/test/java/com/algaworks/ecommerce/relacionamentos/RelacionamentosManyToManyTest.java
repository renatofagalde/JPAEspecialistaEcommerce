package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RelacionamentosManyToManyTest extends EntityManagerTest {

	@Test
	public void method_1_criar_metas() {

		Produto produto = this.entityManager.find(Produto.class, 1);
		Categoria categoria = this.entityManager.find(Categoria.class, 1);

		entityManager.getTransaction().begin();

		produto.setCategorias(Arrays.asList(categoria));

		entityManager.getTransaction().commit();

		entityManager.clear();

		final Categoria verificacao = this.entityManager.find(Categoria.class, categoria.getId());

		Assert.assertFalse(verificacao.getProdutos().isEmpty());

	}

}
