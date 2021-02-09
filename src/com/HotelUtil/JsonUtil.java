package com.HotelUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static ObjectMapper objectMapper;
	static{
		objectMapper=new ObjectMapper();
	}
public static String convertJavaToJson(Object obj)
{
	String jsonString="";
	try {
		jsonString=objectMapper.writeValueAsString(obj);
	} catch (JsonProcessingException e) {
		System.out.println("Exception occurred while converting the java objct into json");
		e.printStackTrace();
	}
	return jsonString;
}
}
