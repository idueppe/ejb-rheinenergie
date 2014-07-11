package com.rheinenergie.jpa.model;

import static org.junit.Assert.*;

import java.util.Arrays;
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
public class JpaTest {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("rheinenergie-test");

	// @PersistenceContext in @Stateless Bean
	private EntityManager em;

	@Before
	public void setUp() {
		em = emf.createEntityManager();
		System.out.println(" ============| EM " + em.toString());
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

		System.out.println("===================== | PARTNER " + partner);

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

		beziehung.setBetreiber(new Betreiber().withName("Düppe"));

		Ansprechpartner partner = new Ansprechpartner().withName("Mair");
		// partner.setBeziehung(beziehung);
		beziehung.setPartner(partner);

		System.out.println("===================== | PARTNER " + partner);

		txCommit();
	}

	@Test
	public void test_6_find_partner() throws Exception {
		System.out.println("--------------------------------------------");
		txBegin();
		Ansprechpartner partner = em
				.createQuery(
						"SELECT p FROM Ansprechpartner p "
								+ " JOIN FETCH p.beziehung"
//								+ " JOIN FETCH p.beziehung.anlage "
//								+ " JOIN FETCH p.beziehung.betreiber "
						,Ansprechpartner.class)
				// .setParameter("name", "Mair")
				.getResultList().get(0);

		System.out.println("===================== | PARTNER " + partner);

		assertEquals("Windanlage", partner.getAnlage().getName());
		txCommit();
	}
	
	@Test
	public void test_7_do_not_find_partner() throws Exception {
		System.out.println("--------------------------------------------");
		List<Ansprechpartner> partners = em
				.createQuery(
						"SELECT p FROM Ansprechpartner p "
								+ " JOIN FETCH p.beziehung"
								+ " JOIN FETCH p.beziehung.anlage "
								+ " JOIN FETCH p.beziehung.betreiber "
								+ " WHERE p.name = :name"
						,Ansprechpartner.class)
						.setParameter("name", "xxxxx_do_not_exists")
						.getResultList();
		
		assertNotNull(partners);
		assertTrue(partners.isEmpty());
	}

	@Test
	public void test_8_find_PartnerInfo() throws Exception {
		List<PartnerInfo> infos
		 = em.createQuery("SELECT "
		 		+ " new com.rheinenergie.jpa.model.PartnerInfo(p.id, p.name, b.name) "
		 		+ " FROM Ansprechpartner p "
		 		+ " LEFT JOIN p.beziehung bz "
		 		+ " LEFT JOIN bz.betreiber b" , PartnerInfo.class)
		 		.getResultList();
		
		System.out.println(Arrays.toString(infos.toArray()));
		
	}
	
	@Test
	public void test_9_CreateAnlage() {
		txBegin();

		Anlage anlage = new Anlage().withName("Windanlage");
		anlage.setDescription("Beschreibung");
		anlage.setImage(new byte[]{1,2,3,4,5,6,7,8});
		em.persist(anlage);

		txCommit();
		txBegin();

		Anlage found = em.find(Anlage.class, anlage.getId());
		found.setName("Updated Name");
		
		em.remove(found);

		txCommit();
	}



	public void txCommit() {
		em.getTransaction().commit();
	}

	public void txBegin() {
		em.getTransaction().begin();
	}

}
