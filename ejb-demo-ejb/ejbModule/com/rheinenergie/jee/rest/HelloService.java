package com.rheinenergie.jee.rest;


import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("hello")
public class HelloService {
	
	@GET
	@Path("/say/{name}")
	@Consumes(
			{MediaType.APPLICATION_XML,
				MediaType.TEXT_PLAIN})
	@Produces(
			{MediaType.APPLICATION_XML,
			 MediaType.APPLICATION_JSON,
			 MediaType.TEXT_PLAIN})
	public String say(@PathParam("name") String name)
	{
		return "Hello name";
	}

}
