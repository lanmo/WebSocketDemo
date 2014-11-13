package com.yn.controllers;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

@Path("")
public class HelloController {
	
	@Get("hello")
	public String hello() {
		return "@hello";
	}
}
