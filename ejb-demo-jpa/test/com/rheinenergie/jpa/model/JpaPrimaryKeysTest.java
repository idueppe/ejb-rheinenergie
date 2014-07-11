package com.rheinenergie.jpa.model;

import static org.junit.Assert.*;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JpaPrimaryKeysTest {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("rheinenergie-test");

	private EntityManager em;

	@Before
	public void setUp() {
		em = emf.createEntityManager();
	}

	@After
	public void tearDown() {
		if (em.isOpen())
			em.close();
	}

	@Test
	public void testCreateSonstigeKontakte() {
		SonstigeKontakte kontakt = new SonstigeKontakte();
		SonstigePK pk = kontakt.getPk();
		pk.setIdA(System.currentTimeMillis());
		pk.setIdB(UUID.randomUUID().toString());
		kontakt.setName("new name");
		
		txBegin();
		em.persist(kontakt);
		txCommit();
		
//		pk.setIdA(kontakt.getIdA());
//		pk.setIdB(kontakt.getIdB());
		
		SonstigeKontakte found = em.find(SonstigeKontakte.class, kontakt.getPk());
		assertNotNull(found);
		
		System.out.println(found);
	}
	
	public void txCommit() {
		em.getTransaction().commit();
	}

	public void txBegin() {
		em.getTransaction().begin();
	}


}
