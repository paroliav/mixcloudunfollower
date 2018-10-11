package com.parolia.vivek.utils;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestHelper {

	public static Response get(String endpoint){
        System.out.println("Request sent: \n" + endpoint);
        Response response = apiConfig().
                when().
                	get(endpoint);
//		System.out.println("Response received: \n" + response.asString());
		return response;
	}

	public static Response delete(String endpoint){
		System.out.println("Request sent: \n" + endpoint);
		Response response = apiConfig().when().delete(endpoint);
		System.out.println("Response received: \n" + response.asString());
		return response;
	}
	
    private static RequestSpecification apiConfig() {
        return givenConfig()
                .header("Content-Type", "application/json");
	}
	
	private static RequestSpecification givenConfig() {
	    RestAssured.baseURI = "";
	    RestAssured.config = RestAssuredConfig.newConfig().sslConfig(SSLConfig.sslConfig().allowAllHostnames());
	    RestAssured.useRelaxedHTTPSValidation();
	    return RestAssured.given().relaxedHTTPSValidation()
	            .header("Accept-Language", "en");
	}
	
}
