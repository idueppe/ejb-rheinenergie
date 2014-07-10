package com.rheinenergie.jee;

import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Local(CalculatorLocal.class)
@Remote({ CalculatorRemote.class, CalculatorLongCallRemote.class })
public class CalculatorBean implements CalculatorRemote, CalculatorLocal, CalculatorLongCallRemote {

	public CalculatorBean() {
	}

	@PostConstruct
	public void setUp() {
		System.out.println("CalculatorBean postcontruct");
	}

	public int add(int a, int b) {
		int result = a + b;
		System.out.println("Calculating " + a + "+" + b + "=" + result);
		return result;
	}
	
	@Asynchronous
	public Future<Long> facultyAsync(int number) {
		return new AsyncResult<Long>(faculty(number));
	}

	@Interceptors(CalculatorCacheInterceptor.class)
	public Long faculty(int number) {
		long result = 1;
		while (number > 1) {
			result *= number--;
			System.out.println(">" + result);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@PreDestroy()
	public void tearDown() {
		System.out.println("CalculatorBean destroying");
	}

}
