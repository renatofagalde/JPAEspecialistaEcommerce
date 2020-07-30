package com.algaworks.ecommerce;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerTest {

	protected static EntityManagerFactory entityManagerFactory;

	protected EntityManager entityManager;

	//antes de tudo
	@BeforeClass
	public static void setUpBeforeClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("ecommerce-persistentunit");
	}

	//depois de tudo
	@AfterClass
	public static void tearDownAfterClass() {
		entityManagerFactory.close();
	}

	//será executado para cada teste
	@Before
	public void setUp() {
		entityManager = entityManagerFactory.createEntityManager();
	}


	@After
	public void tearDown() {
		entityManager.close();
	}



}
