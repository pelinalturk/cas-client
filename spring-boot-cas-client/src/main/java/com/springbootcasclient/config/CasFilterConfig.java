package com.springbootcasclient.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CasFilterConfig {

	@Autowired
	private CasConfig casConfig;
	
	@Bean
	public FilterRegistrationBean authenticationFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new AuthenticationFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("serverName", casConfig.getServiceUrl());
		filterRegistrationBean.addInitParameter("casServerLoginUrl", casConfig.getCasServerLoginUrl());
		filterRegistrationBean.setOrder(3);
		return filterRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean cas20ProxyReceivingTicketValidationFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		Cas20ProxyReceivingTicketValidationFilter cas20ProxyReceivingTicketValidationFilter = new Cas20ProxyReceivingTicketValidationFilter();
		filterRegistrationBean.setFilter(cas20ProxyReceivingTicketValidationFilter);
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("serverName", casConfig.getServiceUrl());
		filterRegistrationBean.addInitParameter("casServerUrlPrefix", casConfig.getCasServerUrlPrefix());
		filterRegistrationBean.setOrder(4);
		return filterRegistrationBean;
	}
	
	@Bean
	public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener>sessionListenerWithMetrics() {
		ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listenerBean = new ServletListenerRegistrationBean<SingleSignOutHttpSessionListener>();
		listenerBean.setListener(new SingleSignOutHttpSessionListener());
		return listenerBean;
	}
	
	@Bean
	public FilterRegistrationBean requestWrapperFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new HttpServletRequestWrapperFilter());
		return bean;
	}
	
	@Bean
	public FilterRegistrationBean singleSignOutFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
		bean.setFilter(singleSignOutFilter);
		bean.addUrlPatterns("/*");
		bean.addInitParameter("serverName", casConfig.getServiceUrl());
		bean.addInitParameter("casServerLogoutUrl", casConfig.getCasServerLogoutUrl());
		bean.setEnabled(true);
		bean.setOrder(0);
		return bean;
	}
}
