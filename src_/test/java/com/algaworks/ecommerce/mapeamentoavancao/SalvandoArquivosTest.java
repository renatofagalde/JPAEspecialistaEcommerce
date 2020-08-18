package com.algaworks.ecommerce.mapeamentoavancao;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class SalvandoArquivosTest extends EntityManagerTest {

    @Test
    public void salvarFotoProduto(){
        entityManager.getTransaction().begin();
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setFoto(carregarFoto());
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, 1);
        Assert.assertNotNull(produtoVerificacao.getFoto());
        Assert.assertTrue(produtoVerificacao.getFoto().length > 0);
    }

    @Test
    public void salvarXmlNota() {
        Pedido pedido = this.entityManager.find(Pedido.class, 1);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date());

        notaFiscal.setXml(carregarNotaFiscal());


        this.entityManager.getTransaction().begin();
        this.entityManager.persist(notaFiscal);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        NotaFiscal verificacao = this.entityManager.find(NotaFiscal.class, 1);

        Assert.assertNotNull(verificacao.getXml());
        Assert.assertTrue(verificacao.getXml().length > 0);


        try {
            OutputStream out = new FileOutputStream(
                    Files.createFile(Paths.get(System.getProperty("user.home") + "/Desktop/xml1.xml")).toFile());
            out.write(verificacao.getXml());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //6.12
//    private static byte[] carregarNotaFiscal() {
//        try {
//            return SalvandoArquivosTest.class.getResourceAsStream("/nota-fiscal.xml").readAllBytes();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private static byte[] carregarFoto() {
        return carregarArquivo("/eu.png");
    }

    private static byte[] carregarNotaFiscal() {
        return carregarArquivo("/nota-fiscal.xml");
    }

    private static byte[] carregarArquivo(String nome) {
            return  null;
//        try {
//            return SalvandoArquivosTest.class.getResourceAsStream(nome).readAllBytes();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

}
