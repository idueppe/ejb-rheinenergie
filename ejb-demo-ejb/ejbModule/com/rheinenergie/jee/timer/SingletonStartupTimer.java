package com.rheinenergie.jee.timer;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton 
public class SingletonStartupTimer {

	@Schedule(second="*/5", minute="0", hour="0", persistent=false)
	public void lifePing() {
		System.out.println(">>> Application is still running <<<");
	}
	
}
