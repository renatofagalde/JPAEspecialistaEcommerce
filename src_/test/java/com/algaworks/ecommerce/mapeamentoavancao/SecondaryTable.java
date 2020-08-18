package com.algaworks.ecommerce.mapeamentoavancao;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;
import org.junit.Assert;
import org.junit.Test;

public class SecondaryTable extends EntityManagerTest {

    @Test
    public void salvarCliente() {

        Cliente cliente = new Cliente();
        cliente.setNome("Nome exemplo SecondaryTable");
        cliente.setSexo(SexoCliente.MASCULINO);

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(cliente);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        Cliente clienteVerificacao = this.entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao.getSexo());

    }
}
