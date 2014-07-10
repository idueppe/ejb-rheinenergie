package de.rheinenergie.ejb;
import javax.naming.NamingException;

import com.rheinenergie.jee.CalculatorRemote;

public class CalculatorClient {

	public static void main(String[] args) throws NamingException {
		String ejbName = "java:global/" //
				+ "ejb-demo-ear/" //
				+ "ejb-demo-ejb/" //
				+ "CalculatorBean" //
				+ "!com.rheinenergie.jee.CalculatorRemote";
		
		CalculatorRemote calculator = (CalculatorRemote) ServiceLocator.lockup(ejbName);
		int result = calculator.add(1, 4);
		System.out.println("Result " + result);
	}

}
