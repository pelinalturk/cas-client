package com.springbootcasclient.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/home")
	public String home(HttpServletRequest httpServletRequest, ModelMap modelMap) {
		HttpSession session = httpServletRequest.getSession();
		Assertion assertion = (Assertion)session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
		modelMap.put("userName", assertion.getPrincipal().getName());
		return "home";
	}
}
