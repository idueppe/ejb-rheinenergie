package de.rheinenergie.ejb;

import static org.junit.Assert.assertEquals;

import javax.naming.NamingException;

import org.junit.Test;

import com.rheinenergie.jee.CalculatorRemote;

public class CalculatorIT {

	String ejbName = "java:global/" //
			+ "ejb-demo-ear/" //
			+ "ejb-demo-ejb/" //
			+ "CalculatorBean" //
			+ "!com.rheinenergie.jee.CalculatorRemote";
	
	@Test
	public void testAdd() throws NamingException {
		CalculatorRemote calc = ServiceLocator.lockup(ejbName);
		assertEquals(6, calc.add(5, 1));
	}

	@Test
	public void testFaculty() throws NamingException {
		CalculatorRemote calc = ServiceLocator.lockup(ejbName);
		assertEquals(Long.valueOf(3628800), calc.faculty(10));
	}

	@Test
	public void testFaculty2() throws NamingException {
		CalculatorRemote calc = ServiceLocator.lockup(ejbName);
		assertEquals(Long.valueOf(3628800), calc.faculty(10));
	}
	
}
