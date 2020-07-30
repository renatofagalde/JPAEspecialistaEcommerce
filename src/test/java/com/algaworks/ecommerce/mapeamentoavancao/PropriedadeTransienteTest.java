package com.algaworks.ecommerce.mapeamentoavancao;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class PropriedadeTransienteTest extends EntityManagerTest {


    @Test
    public void validar_primeiroNome() {
        Cliente cliente = this.entityManager.find(Cliente.class, 1);

        Assert.assertEquals("Fernando",cliente.getPrimeiroNome());
    }
}
