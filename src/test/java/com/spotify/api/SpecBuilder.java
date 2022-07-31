package com.spotify.api;

import java.util.HashMap;

import com.utilities.PropertiesReader;
import static com.spotify.api.TokenManager.getToken;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

	public static String baseURI = "https://api.spotify.com/v1";
	public static String accessToken = "";
	public static String baseURIToken = "https://accounts.spotify.com/api/token";
	public static String fileName = "environment";
    private static String clientID = PropertiesReader.getPropertyValue(fileName, "client_id");
	private static String clientSecret = PropertiesReader.getPropertyValue(fileName, "client_secret");
	private static String refreshToken = PropertiesReader.getPropertyValue(fileName, "refresh_token");
	public static RequestSpecification getRequest() {
		accessToken = getToken();
		return new RequestSpecBuilder().addHeader("Authorization", "Bearer " + accessToken)
				.setContentType(ContentType.JSON).setBaseUri(baseURI).log(LogDetail.ALL).build();

	}

	public static ResponseSpecification getResponse() {
		return new ResponseSpecBuilder().log(LogDetail.ALL).build();
	}

	public static RequestSpecification getRequestForToken() {
		HashMap<String, String> formParam = new HashMap<String, String>();
		formParam.put("client_id", clientID);
		formParam.put("client_secret", clientSecret);
		formParam.put("refresh_token", refreshToken);
		formParam.put("grant_type", "refresh_token");

		return new RequestSpecBuilder().setContentType(ContentType.URLENC).addFormParams(formParam)
				.setBaseUri(baseURIToken).log(LogDetail.ALL).build();

	}

}
