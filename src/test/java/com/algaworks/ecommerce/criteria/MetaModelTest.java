package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.Produto_;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.MessageFormat;
import java.util.List;

public class MetaModelTest extends EntityManagerTest {
    @Test
    public void utilizarMetaModel() {

        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);

        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.or(
                criteriaBuilder.like(root.get(Produto_.nome),"%C%"),
                criteriaBuilder.like(root.get(Produto_.descricao),"%C%")
        ));

        final TypedQuery<Produto> typedQuery = this.entityManager.createQuery(criteriaQuery);
        final List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(o -> {
            final String message = MessageFormat.format("ID -> {0} de nome {1}", o.getId(), o.getNome());
            System.out.println("message = " + message);
        });

    }
}
