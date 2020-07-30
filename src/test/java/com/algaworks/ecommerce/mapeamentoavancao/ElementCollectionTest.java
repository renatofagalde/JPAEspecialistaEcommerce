package com.algaworks.ecommerce.mapeamentoavancao;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Atributo;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class ElementCollectionTest extends EntityManagerTest {

    @Test
    public void aplicarTags() {
        entityManager.getTransaction().begin();

        Produto produto = this.entityManager.find(Produto.class, 1);
        produto.setTags(Arrays.asList("ebook", "livro digital", "no-paper"));

        entityManager.getTransaction().commit();


        entityManager.clear();
        Produto produtoVerificacao = this.entityManager.find(Produto.class, produto.getId());
        Assert.assertFalse(produtoVerificacao.getTags().isEmpty());
    }

    @Test
    public void aplicarAtributos() {


        entityManager.getTransaction().begin();

        Produto produto = this.entityManager.find(Produto.class, 1);
        produto.setAtributos(Arrays.asList(Atributo.builder().nome("tela").valor("320x600").build(), Atributo.builder().nome("peso").valor("96grms").build()));

        entityManager.getTransaction().commit();


        entityManager.clear();
        Produto produtoVerificacao = this.entityManager.find(Produto.class, produto.getId());
        Assert.assertFalse(produtoVerificacao.getAtributos().isEmpty());
    }

    @Test
    public void aplicarContato() {


        entityManager.getTransaction().begin();

        Cliente cliente = this.entityManager.find(Cliente.class, 1);
        cliente.setContatos(Collections.singletonMap("email", "fernando@email.com"));

        entityManager.getTransaction().commit();


        entityManager.clear();
        Cliente clienteVerificacao = this.entityManager.find(Cliente.class, cliente.getId());
        Assert.assertEquals(clienteVerificacao.getContatos().get("email"), "fernando@email.com");
    }
}
