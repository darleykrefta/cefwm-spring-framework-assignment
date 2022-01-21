package com.utfpr.delivery.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	
	public static String getStackTrace(Exception ex) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		ex.printStackTrace(pw);

		String stacktrace = sw.toString();

		return stacktrace;

	}
	
	public static void merge(Object original, Map<String, Object> campos) throws IllegalArgumentException {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);

		Object origem = objectMapper.convertValue(campos, original.getClass());

		campos.forEach((property, value) -> {

			Field field = ReflectionUtils.findField(original.getClass(), property);
			field.setAccessible(true);

			Object newValue = ReflectionUtils.getField(field, origem);

			ReflectionUtils.setField(field, original, newValue);

		});

	}
	
}

