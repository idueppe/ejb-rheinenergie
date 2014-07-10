package com.rheinenergie.jee.timer;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerHandle;
import javax.ejb.TimerService;

@Stateless
@Remote(HelloForEverRemote.class)
public class HelloTimerService implements HelloForEverRemote {
	
	@Resource
	private TimerService timerService;
	
	
	public void sayForEver(String name)
	{
		timerService.createTimer(new Date(),2000l, "Hello " + name);
	}
	
	@Timeout
	public void say(Timer timer) {
		System.out.println(timer.getInfo());
		// do something 
		System.out.println("see you at " + timer.getNextTimeout()+" again.");
	}
	
	public void enough()
	{
		for(Timer timer : timerService.getTimers())
		{
			System.out.println("enough for "+timer.getInfo());
			timer.cancel();
		}
	}
	

}
