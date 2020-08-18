package com.algaworks.ecommerce.util;

import com.algaworks.ecommerce.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IniciarUnidadePersistencia {

	final static Logger logger = Logger.getLogger(IniciarUnidadePersistencia.class.toString());

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ecommerce-persistentunit");
		final EntityManager entityManager = entityManagerFactory.createEntityManager();

		//codigo
		Produto produto = entityManager.find(Produto.class, 1);
		System.out.println("produto = " + produto.getDescricao());
		final String log = MessageFormat.format("Produto {0}", produto.getDescricao());


		logger.log(Level.INFO,log);
		entityManager.close();
		entityManagerFactory.close();

	}
}
