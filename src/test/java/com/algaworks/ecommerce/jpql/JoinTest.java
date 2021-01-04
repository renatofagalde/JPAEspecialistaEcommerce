package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class JoinTest extends EntityManagerTest {

    //9.8
    @Test
    public void usarJoinFetch() {

        String jpql = "select p from Pedido p "
                + " left join fetch p.pagamento "
                + " join fetch p.cliente "
                + " left join fetch p.notaFiscal ";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test //9.7
    public void fazerLeftJoin() {
        String jpql = "select p from Pedido p left join p.pagamento pag on pag.status = 'PROCESSANDO'";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    //9.6 exemplos join jpql
    @Test
    public void fazerJoin() {

        String jpql = "select p from Pedido p inner join p.pagamento";
        TypedQuery<Pedido> pedidoTypedQuery = this.entityManager.createQuery(jpql, Pedido.class);

        final List<Pedido> pedidoList = pedidoTypedQuery.getResultList();
        Assert.assertTrue(pedidoList.size() > 0);
    }
}
