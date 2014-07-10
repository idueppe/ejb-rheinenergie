package com.rheinenergie.jee.sfsb;


public interface CounterRemote {

	public void reset();

	public int increment();

	public void off();

}