package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ExpressoesCondicionaisTest extends EntityManagerTest {

    @Test
    public void usarDistinct() {
        String jpql = " select distinct p from Pedido p join p.itens i join i.produto pro " +
                " where pro.id in (1,2,3,4)";

        TypedQuery<Pedido> typedQuery = this.entityManager.createQuery(jpql, Pedido.class);
        final List<Pedido> pedidos = typedQuery.getResultList();
        Assert.assertFalse(pedidos.isEmpty());

        System.out.println("pedidos.size() = " + pedidos.size());
    }

    @Test
    public void usarExpressaoIN() {
        final Cliente cliente1 = this.entityManager.find(Cliente.class, 1);
        final Cliente cliente2 = this.entityManager.find(Cliente.class, 2);

        //obrigatorio um objeto, se passar um arrary vazio da erro de sintaxe
        List<Cliente> clientes = Arrays.asList(cliente1,cliente2);

        String jpql="select p from Pedido p where p.cliente in :clientes";

        TypedQuery<Pedido> typedQuery = this.entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("clientes", clientes);
        final List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(System.out::println);
        lista.forEach(objeto -> System.out.println(objeto.getId()+"".concat("\t").concat(objeto.getStatus().toString())));

    }


    //9.32 case para o tipo da classe quando usado na herenca
    @Test
    public void usarExpressaoCase() {
        String jpql = "select p.id, " +
                "case p.status " +
                "   when 'PAGO' then 'Está pago' " +
                "   when 'CANCELADO' then 'Foi cancelado' " +
                "   else 'Aguardando' " +
                " end " +
                "from Pedido p";

        TypedQuery<Object[]> typedQuery = this.entityManager.createQuery(jpql, Object[].class);
        final List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(System.out::println);
        lista.forEach(objeto -> System.out.println(objeto[0].toString().concat("\t").concat(objeto[1].toString())));
    }

    @Test
    public void usarExpressaoCaseTipado() {
        String jpql = "from Pedido p";

        TypedQuery<Pedido> typedQuery = this.entityManager.createQuery(jpql, Pedido.class);
        final List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(System.out::println);
        lista.forEach(objeto -> System.out.println(objeto.getId()+"".concat("\t").concat(objeto.getStatus().toString())));
    }

    @Test
    public void usarExpressaoDiferente() {
        String jpql = "select p from Produto p where p.preco <> 100";
        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarBetween() {

        String jpql = "select p from Pedido p where p.dataCriacao between :dataInicial and :dataFinal";
        TypedQuery<Pedido> typedQuery = this.entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("dataInicial", LocalDateTime.now().minusDays(2));
        typedQuery.setParameter("dataFinal", LocalDateTime.now());

        final List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarMaiorMenorComDatas() {
        String jpql = "select p from Pedido p where p.dataCriacao > :data";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("data", LocalDateTime.now().minusDays(2));

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarMaiorMenor() {

        String jpql = "select p from Produto p where p.preco >= :preco";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        typedQuery.setParameter("preco", new BigDecimal(499));


        List<Object[]> lista = typedQuery.getResultList();

        final String isEmpty = MessageFormat.format("A lista está empty? {0}, com {1} elementos", lista.isEmpty(),lista.size());
        System.out.println("isEmpty = " + isEmpty);
        Assert.assertFalse(lista.isEmpty());

    }

    //isNull para propriedade
    @Test
    public void usarIsNull() {

        String jpql = "select p from Produto p where p.descricao is not null";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        final String isEmpty = MessageFormat.format("A lista está empty? {0}, com {1} elementos", lista.isEmpty(),lista.size());
        System.out.println("isEmpty = " + isEmpty);
        Assert.assertFalse(lista.isEmpty());

    }

    //isEmpty para colecoes
    @Test
    public void usarIsEmpty() {
        String jpql = "select p from Produto p where p.categorias is not empty";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        final String isEmpty = MessageFormat.format("A lista está empty? {0}", lista.isEmpty());
        System.out.println("isEmpty = " + isEmpty);
        Assert.assertFalse(lista.isEmpty());

    }


    @Test
    public void usarExpressaoCondicionalLike() {

        String jpql = "select c from Cliente c where c.nome like concat('%', :nome, '%')";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        typedQuery.setParameter("nome", "a");

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
