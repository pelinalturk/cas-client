package com.springbootcasclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "cas")
public class CasConfig {

	private String serviceUrl;
	
	private String casServerUrlPrefix;
	
	private String casServerLoginUrl;
	
	private String casServerLogoutUrl;
}
