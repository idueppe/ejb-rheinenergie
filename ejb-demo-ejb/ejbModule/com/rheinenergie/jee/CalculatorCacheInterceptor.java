package com.rheinenergie.jee;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class CalculatorCacheInterceptor {
	
	private Map<Integer, Long> cache = new HashMap<Integer, Long>();

	@AroundInvoke
	public Object cacheResults(InvocationContext ctx) throws Exception
	{
		System.out.println("Target > "+ctx.getTarget().getClass());
		System.out.println("Method > "+ctx.getMethod().getName());
		System.out.println("Parameter> "+Arrays.toString(ctx.getParameters()));
		
		Integer value = (Integer) ctx.getParameters()[0];
		if (cache.containsKey(value))
		{
			return cache.get(value);
		}
		
		Object result = ctx.proceed();
		
		cache.put(value, (Long) result);
		
		return result;
	}
	
}
