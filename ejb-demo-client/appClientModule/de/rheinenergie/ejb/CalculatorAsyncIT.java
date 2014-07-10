package de.rheinenergie.ejb;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.Future;

import org.junit.Test;

import com.rheinenergie.jee.CalculatorLongCallRemote;

public class CalculatorAsyncIT {

	String ejbName = "java:global/" //
			+ "ejb-demo-ear/" //
			+ "ejb-demo-ejb/" //
			+ "CalculatorBean" //
			+ "!com.rheinenergie.jee.CalculatorLongCallRemote";
	
	@Test
	public void testFaculty() throws Exception {
		CalculatorLongCallRemote calc = ServiceLocator.lockup(ejbName);
		Future<Long> faculty = calc.facultyAsync(20);
		
		int i = 0;
		while (!faculty.isDone())
		{
			System.out.print(".");
			Thread.sleep(1000);
		}
		System.out.println("Faculty is "+faculty.get());
		
		assertEquals(Long.valueOf(2432902008176640000L), faculty.get());
	}
	
}
