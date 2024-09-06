package com.test.apitesting.utils;

import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;

public class Base {

	@BeforeMethod
	public void beforeMethod()
	{
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); 
	}
}
