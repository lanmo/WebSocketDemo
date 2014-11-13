package com.yn.config;

import java.util.Properties;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 属性注入
 * @author yangnan
 *
 */
@Component
public class PropertyConfigBean implements InitializingBean, FactoryBean<Object> {

	private Configuration[] configurations;
	
	private CompositeConfiguration compositeConfiguration;
	
	private String profile = "spring.profiles.active";
	

	public Class<?> getObjectType() {
		return Properties.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public void afterPropertiesSet() throws Exception {
		
		if(compositeConfiguration == null) {
			compositeConfiguration = new CompositeConfiguration();
		}
		
		String envType = System.getProperty(profile);
		if("local".equals(envType.trim())) {
			compositeConfiguration.addConfiguration(configurations[0]);
		} else if("test".equals(envType.trim())) {
			compositeConfiguration.addConfiguration(configurations[0]);
		}
	}
	
	public Object getObject() throws Exception {
		return compositeConfiguration == null ? null : ConfigurationConverter.getProperties(compositeConfiguration);
	}
	
	public Object getProperty(String key) {
		return compositeConfiguration.getProperty(key);
	}
}
