package de.rheinenergie.ejb.timer;

import org.junit.Test;

import com.rheinenergie.jee.timer.HelloForEverRemote;

import de.rheinenergie.ejb.ServiceLocator;

public class TimerIT {

	String ejbName = "java:global/" //
			+ "ejb-demo-ear/" //
			+ "ejb-demo-ejb/" //
			+ "HelloTimerService" //
			+ "!com.rheinenergie.jee.timer.HelloForEverRemote";
	
	@Test
	public void testSayForEver() throws Exception {
		HelloForEverRemote helloForEver = ServiceLocator.lockup(ejbName);
		helloForEver.sayForEver("EJB 3.2");
		Thread.sleep(1500);
		helloForEver.sayForEver("JPA 2.1");
		Thread.sleep(1600);
		helloForEver.sayForEver("JSF 2.2");
		
		Thread.sleep(20000);
		
		helloForEver.enough();
	}
	
}
