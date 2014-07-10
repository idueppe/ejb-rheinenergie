package com.rheinenergie.jee.ws;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.rheinenergie.jee.CalculatorLocal;

@Stateless
@Remote(CalculatorWSRemote.class)
@WebService
public class CalculatorWSBean implements CalculatorWSRemote {
	
	@EJB
	private CalculatorLocal calculator;
	
	@Override
	@WebMethod
	@WebResult(name="sum")
	public Integer add(
			@WebParam(name="Wert1") Integer a, 
			@WebParam(name="Wert2") Integer b)
	{
		return calculator.add(a, b);
	}

}
