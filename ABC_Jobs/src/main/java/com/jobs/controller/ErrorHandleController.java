package com.jobs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorHandleController {
	
	@GetMapping(value = "errors")
	public String renderErrorPages(HttpServletRequest request) {
		
		return "notFound";
	}
	
}
