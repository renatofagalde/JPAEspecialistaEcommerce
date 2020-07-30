package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

public class RelacionamentosOneToManyTest extends EntityManagerTest {

	@Test
	public void verificarRelacionamento() {

		Cliente cliente = this.entityManager.find(Cliente.class, 1);

		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setTotal(BigDecimal.TEN);

		pedido.setCliente(cliente);

		this.entityManager.getTransaction().begin();
		this.entityManager.persist(pedido);
		this.entityManager.getTransaction().commit();

		this.entityManager.clear();

		Cliente verificacao = this.entityManager.find(Cliente.class, cliente.getId());

		Assert.assertFalse(verificacao.getPedidos().isEmpty());
	}

	@Test
	public void verificarRelacionamentoPedido() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);

		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setTotal(BigDecimal.TEN);
		pedido.setCliente(cliente);

		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setPrecoProduto(produto.getPreco());
		itemPedido.setQuantidade(1);
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);

		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.persist(itemPedido);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertFalse(pedidoVerificacao.getItens().isEmpty());
	}

	@Test
	public void pedidoVazio() {

		Pedido pedido = new Pedido();
		pedido.setItens(Collections.emptyList());

		Assert.assertTrue(pedido.getItens().isEmpty());
	}
}
