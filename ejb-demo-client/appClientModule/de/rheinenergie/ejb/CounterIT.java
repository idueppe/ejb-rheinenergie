package de.rheinenergie.ejb;

import static org.junit.Assert.assertEquals;

import javax.naming.NamingException;

import org.junit.Test;

import com.rheinenergie.jee.sfsb.CounterRemote;

public class CounterIT {

	private static final String ejbName = "java:global/" //
			+ "ejb-demo-ear/" //
			+ "ejb-demo-ejb/" //
			+ "CounterBean" //
			+ "!com.rheinenergie.jee.sfsb.CounterRemote";
	
	@Test
	public void testIncrement() throws NamingException {
		CounterRemote counter = ServiceLocator.lockup(ejbName);
		assertEquals(1, counter.increment());
		assertEquals(2, counter.increment());
	}

	@Test
	public void testReset() throws NamingException {
		CounterRemote counter = ServiceLocator.lockup(ejbName);
		assertEquals(1, counter.increment());
		counter.reset();
		assertEquals(1, counter.increment());
	}
	
	
	@Test
	public void testTimeout() throws Exception {
		CounterRemote counter = ServiceLocator.lockup(ejbName);
		assertEquals(1, counter.increment());
		Thread.sleep(30000);
		assertEquals(2, counter.increment());
	}
	
	
	@Test(expected=javax.ejb.NoSuchEJBException.class)
	public void testRemove() throws Exception {
		CounterRemote counter = ServiceLocator.lockup(ejbName);
		counter.off();
	    counter.increment();
	}

}