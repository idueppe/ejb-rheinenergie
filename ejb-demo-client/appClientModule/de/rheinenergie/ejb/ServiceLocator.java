package de.rheinenergie.ejb;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {

	public static Hashtable<String,String> weblogicConfig() {
		Hashtable<String,String> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, "t3://localhost:7001");
		env.put(Context.SECURITY_PRINCIPAL, "admin");
		env.put(Context.SECURITY_CREDENTIALS, "adm!n123");
		return env;
	}

	@SuppressWarnings("unchecked")
	public static <T> T lockup(String ejbName)
			throws NamingException {
		InitialContext context = new InitialContext(weblogicConfig());
		return (T) context.lookup(ejbName);
	}

	
	
	
	
	
	
	
	
	
	
	
}
