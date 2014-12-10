package com.yn.config;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.yn.util.L;

/**
 * 属性注入
 * @author yangnan
 *
 */
public class ConfigBean implements InitializingBean, FactoryBean<Object> {

	private Map<String, Configuration> map;
	
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
		
		String envType = System.getProperty(profile, "local");
		L.dr("当前运行环境............................"+envType);
		
		Configuration configuration = map.get(envType.trim());
		compositeConfiguration.addConfiguration(configuration);
		
	}
	
	public Object getObject() throws Exception {
		return compositeConfiguration == null ? null : ConfigurationConverter.getProperties(compositeConfiguration);
	}
	
	public Object getProperty(String key) {
		return compositeConfiguration.getProperty(key);
	}

	public Map<String, Configuration> getMap() {
		return map;
	}

	public void setMap(Map<String, Configuration> map) {
		this.map = map;
	}
}
