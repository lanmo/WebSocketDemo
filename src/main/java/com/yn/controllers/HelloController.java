package com.yn.controllers;

import com.yn.util.L;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

@Path("")
public class HelloController {
	
	@Get("hello")
	public String hello() {
		L.d("asdasd");
		return "@hello";
	}
}
