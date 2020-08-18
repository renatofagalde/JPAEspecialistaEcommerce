package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.EnderecoEntregaPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MapeamentoObjetoEmbutido extends EntityManagerTest {

	@Test
	public void analisarMapeamentoObjetoEmbutido() {

		EnderecoEntregaPedido endereco = new EnderecoEntregaPedido();
		endereco.setCep("00000-00");
		endereco.setLogradouro("Rua das Laranjeiras");
		endereco.setNumero("123");
		endereco.setBairro("Centro");
		endereco.setCidade("Uberlândia");
		endereco.setEstado("MG");


		Pedido pedido = new Pedido();
//		pedido.setId(1); // comentado para resolucao do exercicio cliente optional=false -> https://www.algaworks.com/aulas/3234/exercicio-usando-o-atributo-optional/
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setTotal(new BigDecimal(10000));
		pedido.setEnderecoEntrega(endereco);
		pedido.setCliente(this.entityManager.find(Cliente.class, 1)); //https://www.algaworks.com/aulas/3234/exercicio-usando-o-atributo-optional/


		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();

		//limpar o pedido da memoria
		entityManager.clear();

		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao);
		Assert.assertNotNull(pedidoVerificacao.getEnderecoEntrega());
		Assert.assertNotNull(pedidoVerificacao.getEnderecoEntrega().getCep());
	}
}
