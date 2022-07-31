package com.restAssure.api;

import static com.spotify.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class RestAssureAPIWrapper {
	
	public static Response post(String path,Object requestPlaylist) {
		return given(getRequest())
				.body(requestPlaylist)
				.when()
				.post(path)
				.then()
				.spec(getResponse())
				.extract().response();
	}
	
	public static Response postToken() {
		return given(getRequestForToken()) 
		.post()
		.then()
		.spec(getResponse())
		.extract()
		.response();
	}
	
	public static Response get(String path) {
		return given(getRequest())
				.when()
				.get(path)
				.then()
				.spec(getResponse())
				.extract().response();
	}
	
	public static Response update(String path,Object requestPlaylist) {
		return given(getRequest())
				.body(requestPlaylist)
				.when()
				.put(path)
				.then()
				.spec(getResponse())
				.extract()
				.response();
	}
	
	
	

}
