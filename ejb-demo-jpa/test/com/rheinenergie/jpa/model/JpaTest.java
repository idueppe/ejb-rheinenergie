package com.rheinenergie.jpa.model;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JpaTest {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("rheinenergie-test");

//	@PersistenceContext in @Stateless Bean
	private EntityManager em;
	
	@Before
	public void setUp() {
		System.out.println("----------------------------------------------------");
		em = emf.createEntityManager();
	}
	
	@After
	public void tearDown() {
		if (em.isOpen())
			em.close();
	}
	
	@Test
	public void test_1() {
		em.close();
	}

	@Test
	public void test_2_CreateAnlage() {
		txBegin();
		
		Anlage anlage = new Anlage().withName("Windanlage");
		em.persist(anlage);
		
		txCommit();
		txBegin();
		
		Anlage found = em.find(Anlage.class, anlage.getId());
		found.setName("Updated Name");
		
		txCommit();
	}
	
	@Test
	public void test_3_CreateAnlageWithBeziehung() throws Exception {
		txBegin();
		
		Anlage anlage = new Anlage().withName("Windanlage");
		BetreiberBeziehung beziehung = new BetreiberBeziehung();
		beziehung.setAnlage(anlage);

		Ansprechpartner partner = new Ansprechpartner().withName("Düppe");
		beziehung.setPartner(partner);
		
		anlage.getBetreiberBeziehungen().add(beziehung);
		
		
		em.persist(anlage);
		
		txCommit();
	}
	
	@Test
	public void test_4_update_beziehung() throws Exception {
		txBegin();
		
		Anlage anlage = //
				em.createQuery("SELECT a FROM Anlage a", Anlage.class) //
					.getResultList() //
					.get(1); //
		
		BetreiberBeziehung beziehung = anlage.getBetreiberBeziehungen().get(0);
		beziehung.getPartner().setName("update");
		
		txCommit();
	}
	
	@Test
	public void test_5_set_Partner() throws Exception {
		txBegin();
		
		Anlage anlage = //
				em.createQuery("SELECT a FROM Anlage a", Anlage.class) //
				.getResultList() //
				.get(1); //
		
		BetreiberBeziehung beziehung = anlage.getBetreiberBeziehungen().get(0);
		
		Ansprechpartner partner = new Ansprechpartner().withName("Mair");
//		partner.setBeziehung(beziehung);
		beziehung.setPartner(partner);
		
		txCommit();
	}

	@Test
	public void test_6_find_partner() throws Exception {
		txBegin();
		Ansprechpartner partner = 
	      em.createQuery("SELECT p FROM Ansprechpartner p WHERE p.name=:name", Ansprechpartner.class)
		  .setParameter("name", "Mair")
		  .getResultList().get(0);
		
		assertEquals("Windanlage", partner.getAnlage().getName());
		txCommit();
	}

	
	public void txCommit() {
		em.getTransaction().commit();
	}

	public void txBegin() {
		em.getTransaction().begin();
	}

}
