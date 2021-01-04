package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.dto.ProdutoDTO;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;

public class BasicoCriteriaTest extends EntityManagerTest {

    @Test
    public void ordenarResultados() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        Root<Cliente> root = criteriaQuery.from(Cliente.class);

        criteriaQuery.orderBy(criteriaBuilder.desc(root.get(Cliente_.nome)));

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Cliente> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(c -> System.out.println(c.getId() + ", " + c.getNome()));
    }
    @Test
    public void fazerJoin() {

        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        Join<Pedido, Pagamento> join = root.join("pagamento");

        //Fetch carrega num só select
/*
        root.fetch("notaFiscal", JoinType.LEFT);
        root.fetch("pagamento", JoinType.LEFT);
        root.fetch("cliente");
*/

/*
        root.join("notaFiscal");
        root.join("cliente");
        root.join("pagamento");
        root.join("cliente");
*/


        criteriaQuery.select(root);
        final TypedQuery<Pedido> typedQuery = this.entityManager.createQuery(criteriaQuery);
        final List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());


        lista.forEach(o -> {
            final String message = MessageFormat.format("ID -> {0} de nome {1}", o.getId(),o.getDataConclusao());
            System.out.println("message = " + message);
        });


/*
        criteriaQuery.multiselect(criteriaBuilder.construct(PedidoDTO.class, root.get("id"), root.get("nome")));

        final TypedQuery<PedidoDTO> typedQuery = this.entityManager.createQuery(criteriaQuery);
        final List<PedidoDTO> lista = typedQuery.getResultList();
*/
    }

    //10.6
    @Test
    public void projetarOResultadoDTO() {

        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<ProdutoDTO> criteriaQuery = criteriaBuilder.createQuery(ProdutoDTO.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.multiselect(criteriaBuilder.construct(ProdutoDTO.class, root.get("id"), root.get("nome")));

        final TypedQuery<ProdutoDTO> typedQuery = this.entityManager.createQuery(criteriaQuery);
        final List<ProdutoDTO> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());


        lista.forEach(o -> {
            final String message = MessageFormat.format("ID -> {0} de nome {1}", o.getId(), o.getNome());
            System.out.println("message = " + message);
        });
    }

    //com alias
    @Test
    public void projetarOResultadoTuple_3() {
        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(criteriaBuilder.tuple(root.get("id").alias("id"), root.get("nome").alias("nome")));

        final TypedQuery<Tuple> typedQuery = this.entityManager.createQuery(criteriaQuery);
        final List<Tuple> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());


        lista.forEach(tuple -> {
            final String message = MessageFormat.format("ID -> {0} de nome {1}", tuple.get("id"), tuple.get("nome"));
            System.out.println("message = " + message);
        });
    }

    @Test
    public void projetarOResultadoTuple_2() {
        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(criteriaBuilder.tuple(root.get("id"), root.get("nome")));

        final TypedQuery<Tuple> typedQuery = this.entityManager.createQuery(criteriaQuery);
        final List<Tuple> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(tuple -> System.out.println(tuple.get(0) + "\t" + tuple.get(1)));
    }

    @Test
    public void projetarOResultadoTuple_1() {
        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.multiselect(root.get("id"), root.get("nome"));

        final TypedQuery<Tuple> typedQuery = this.entityManager.createQuery(criteriaQuery);
        final List<Tuple> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(tuple -> System.out.println(tuple.get(0) + "\t" + tuple.get(1)));
    }

    @Test
    public void projetarOResultado() {
        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.multiselect(root.get("id"), root.get("nome"));

        final TypedQuery<Object[]> typedQuery = this.entityManager.createQuery(criteriaQuery);
        final List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(object -> System.out.println(object[0] + "\t" + object[1]));
    }


    @Test
    public void retornarTodoProdutosExercicio() {

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Produto> queryProduto = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = queryProduto.from(Produto.class);
        queryProduto.select(root);
        final TypedQuery<Produto> typedQuery = this.entityManager.createQuery(queryProduto);

        final List<Produto> produtos = typedQuery.getResultList();
        //produtos deverá ter beans
        Assert.assertFalse(produtos.isEmpty());


    }

    @Test
    public void selecionarUmAtributoParaRetorno() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> criteriaQuery = criteriaBuilder.createQuery(BigDecimal.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root.get("total"));

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));

        TypedQuery<BigDecimal> typedQuery = entityManager.createQuery(criteriaQuery);
        BigDecimal total = typedQuery.getSingleResult();
        Assert.assertEquals(new BigDecimal("2398.00"), total);
    }

    @Test
    public void buscarPorIdentificador() {

        //optional = false faz o lazy funcionar pq garante que existe

        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class); //a entidade que será retornada
        Root<Pedido> root = criteriaQuery.from(Pedido.class); //no from basicamente é a entidade raiz

        criteriaQuery.select(root); //necessario apenas quando a entidade for diferente do informado no root
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));

        final TypedQuery<Pedido> typedQuery = this.entityManager.createQuery(criteriaQuery);
        final List<Pedido> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }
}
