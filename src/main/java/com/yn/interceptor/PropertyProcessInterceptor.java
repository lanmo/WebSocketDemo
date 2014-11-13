package com.yn.interceptor;

import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import com.yn.annotation.PropertyConfig;
import com.yn.config.PropertyConfigBean;

@Component
public class PropertyProcessInterceptor extends InstantiationAwareBeanPostProcessorAdapter {

	@Autowired
	private PropertyConfigBean configBean;
	
	private SimpleTypeConverter convert = new SimpleTypeConverter();
	
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		
		if("appConstants".equals(beanName)) {
			findPropertyAutowireValue(bean, beanName);
		}
		
		return bean;
	}

	private void findPropertyAutowireValue(Object bean, String beanName) {
		ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {
			public void doWith(Field field) throws IllegalArgumentException,
					IllegalAccessException {
				PropertyConfig config = field.getAnnotation(PropertyConfig.class);
				if(config != null) {
					Object value = configBean.getProperty(config.value());
					if(value != null) {
						value = convert.convertIfNecessary(value, field.getType());
						ReflectionUtils.makeAccessible(field);
						ReflectionUtils.setField(field, null, value);
					}
				}
			}
		});
	}

}
