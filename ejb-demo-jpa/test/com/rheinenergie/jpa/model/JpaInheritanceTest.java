package com.rheinenergie.jpa.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JpaInheritanceTest {

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
	public void test_1_CreateErrichter() {
		txBegin();
		Errichter errichter = new Errichter();
		
		errichter.setEmail("email");
		errichter.setErrichterInfo("info");
		
		em.persist(errichter);
		txCommit();
	}
	
	@Test
	public void test_2_CreateBetreiber() {
		txBegin();
		Betreiber betreiber = new Betreiber().withName("info");
		em.persist(betreiber);
		txCommit();
	}
	
	@Test
	public void test_3_FindKontakte() throws Exception {
		List<Kontakt> list = em
				.createQuery("SELECT k FROM Kontakt k",Kontakt.class)
				.getResultList();
		
		for (Kontakt kontakt : list)
		{
			if (kontakt instanceof Errichter)
			{
				System.out.println("Errichter "+ kontakt);
			} else if (kontakt instanceof Betreiber) {
				System.out.println("Betreiber "+ kontakt);
			} else {
				System.out.println("Kontakt" + kontakt);
			}
		}
		
	}
	
	
	
	public void txCommit() {
		em.getTransaction().commit();
	}

	public void txBegin() {
		em.getTransaction().begin();
	}


}
