package com.rheinenergie.jee;

import java.util.concurrent.Future;


public interface CalculatorLongCallRemote {
	
	public Future<Long> facultyAsync(int number);

}
