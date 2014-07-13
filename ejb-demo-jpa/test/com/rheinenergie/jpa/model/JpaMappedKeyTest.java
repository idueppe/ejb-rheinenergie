package com.rheinenergie.jpa.model;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map.Entry;
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
public class JpaMappedKeyTest {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("rheinenergie-test");

	private EntityManager em;

	private static String betreiberGruppeName;
	private static Long betreiberId;
	

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
	public void test_1_CreateSonstigeKontakte() {
		txBegin();
		List<Betreiber> betreiberListe = em
				.createQuery("SELECT b FROM Betreiber b", Betreiber.class) //
				.getResultList(); //
		
		betreiberGruppeName = UUID.randomUUID().toString();
		BetreiberGruppe gruppe = new BetreiberGruppe().withName(betreiberGruppeName);
		for (Betreiber betreiber : betreiberListe)
		{
			System.out.println("BETREIBER: "+betreiber.getName() +" "+ betreiber.getId());
			gruppe.getBetreiberMap().put(betreiber.getName(), betreiber);
		}
		
		String betreiberName = UUID.randomUUID().toString();
		Betreiber betreiber = new Betreiber().withName(betreiberName);
		gruppe.getBetreiberMap().put(betreiberName, betreiber);
		
		em.persist(gruppe);
		txCommit();
		betreiberId = betreiber.getId();
	}
	
	@Test
	public void test_2_FindBetreiberGruppe() throws Exception {
		BetreiberGruppe gruppe = em
				.createQuery("SELECT g FROM BetreiberGruppe g WHERE g.name = :name",BetreiberGruppe.class)
				.setParameter("name", betreiberGruppeName)
				.getResultList().get(0);
		
		for (Entry<String, Betreiber> entry : gruppe.getBetreiberMap().entrySet())
		{
			System.out.println(" "+entry.getKey() + "= "+entry.getValue().getId());
		}
		
	}
	
	
	@Test
	public void test_3_member_of() throws Exception {
		Betreiber betreiber = em.find(Betreiber.class, betreiberId);
		BetreiberGruppe gruppe = em
				.createQuery("SELECT g FROM BetreiberGruppe g "
						+ " WHERE :betreiber member of g.betreiberMap",BetreiberGruppe.class)
		
						// FROM ... g LEFT JOIN g.betreiber b WHERE b = :betreiber
						
						
				.setParameter("betreiber", betreiber)
				.getResultList().get(0);
		assertTrue(gruppe.getBetreiberMap().containsKey(betreiber.getName()));
	}
	
	
	public void txCommit() {
		em.getTransaction().commit();
	}

	public void txBegin() {
		em.getTransaction().begin();
	}


}
