package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class RelacionamentosOneToOneTest extends EntityManagerTest {

	@Test
	public void verificarRelacionamentoPagamentoCartao() {

		Pedido pedido = this.entityManager.find(Pedido.class, 1);

		final PagamentoCartao pagamentoCartao = new PagamentoCartao();
		pagamentoCartao.setNumeroCartao("12345");
		pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
		pagamentoCartao.setPedido(pedido);


		entityManager.getTransaction().begin();
		entityManager.persist(pagamentoCartao);
		entityManager.getTransaction().commit();

		entityManager.clear();


		final Pedido verificacao = this.entityManager.find(Pedido.class, pedido.getId());

		assertNotNull(verificacao.getPagamento());


	}

	@Test
	public void verificarRelacionamentoNotaFiscal() {

		Pedido pedido = this.entityManager.find(Pedido.class, 1);

		final NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setDataEmissao(new Date());
		notaFiscal.setXml("<root>teste</root>".getBytes());
		notaFiscal.setPedido(pedido);


		entityManager.getTransaction().begin();
		entityManager.persist(notaFiscal);
		entityManager.getTransaction().commit();

		entityManager.clear();


		final Pedido verificacao = this.entityManager.find(Pedido.class, pedido.getId());

		assertNotNull(verificacao.getPagamento());


	}
    @Test
    public void buscarPagamentos() {
        List<Pagamento> pagamentos = entityManager
                .createQuery("select p from Pagamento p")
                .getResultList();

        Assert.assertFalse(pagamentos.isEmpty());
    }

    @Test
    public void incluirPagamentoPedido() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        PagamentoCartao pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setPedido(pedido);
        pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
        pagamentoCartao.setNumeroCartao("123");

        entityManager.getTransaction().begin();
        entityManager.persist(pagamentoCartao);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao.getPagamento());
    }

}
