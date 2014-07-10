package com.rheinenergie.jee.sfsb;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

@Stateful
@Remote(CounterRemote.class)
@StatefulTimeout(unit=TimeUnit.SECONDS, value=1)
public class CounterBean implements Serializable, CounterRemote {

	private static final long serialVersionUID = 1L;

	private int counter = 0;

	@PostConstruct
	public void setUp() {
		System.out.println("setup counter bean"+hashCode());
	}
	
	@PreDestroy
	public void tearDown() {
		System.out.println("teardown counter bean "+hashCode());
	}
	
	@PostActivate
	public void activating() {
		System.out.println("activating counter bean "+hashCode());
	}
	
	@PrePassivate
	public void passivating() {
		System.out.println("passivating counter bean"+hashCode());
	}

	@Override
	public int increment() {
		System.out.println("make increment");
		return ++counter;
	}

	public void reset() {
		System.out.println("reset");
		counter = 0;
	}

	@Override
	@Remove
	public void off() {
		System.out.println("reset");
	}

}
