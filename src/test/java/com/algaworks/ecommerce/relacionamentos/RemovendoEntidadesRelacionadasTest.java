package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

public class RemovendoEntidadesRelacionadasTest extends EntityManagerTest {

	@Test
	public void removerEntidadeRelacionada(){
		Pedido pedido = entityManager.find(Pedido.class, 1);
		System.out.println("pedido.getItens().isEmpty() = " + pedido.getItens().isEmpty());
		Assert.assertFalse(pedido.getItens().isEmpty());



		entityManager.getTransaction().begin();
		pedido.getItens().forEach(i -> entityManager.remove(i));
		entityManager.remove(pedido);
		entityManager.getTransaction().commit();


		entityManager.clear();

		Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
		Assert.assertNull(pedidoVerificacao);

	}
}
