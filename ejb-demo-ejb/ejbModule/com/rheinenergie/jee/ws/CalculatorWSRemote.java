package com.rheinenergie.jee.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

public interface CalculatorWSRemote {

	public Integer add(Integer a, Integer b);

}