package com.spotify.testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import com.spotify.application.api.PlayListAPI;
import com.spotify.pojo.PlayList;
import com.spotify.pojo.RootError;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PlayListTestCase {
	
	String uri = "https://api.spotify.com/v1";
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	//public String accessToken = "BQCaqeVKD7NfZemYz03lEEnIK6dXLSmQoWtPxofrIILn1cmCEyWPZi8yUhDRQcCyO5sciuBMBhI-bSLLq1J4U7Ks9MFFUBkzaRT-xImyRmBflfVNBRCufNavqgOG2LiiYTZWyBhazzPwQuctCvxgjE1rpO7S0odNrp_vHGbHsnDA4We8MNyajygMci0YfPZl4e1Elxolvz7lT55HFj_Mmvrw6tuUiA4SEs6Ad4CUdPOws5yquKVPUs8M01-JD8oEPO2G91L0Rd0PGUmx";
	String user_id = "31arqryw45n666qdjjrr62rn6yfa";
	String playListID  = "";
	String newRequestName = "";
	String newRequestDesc = "";
	boolean newRequestPublic;
	
	
	@Test(priority = 1)
	public void createPlayList() {
		
		PlayList requestPlayList = new PlayList();
		requestPlayList.setName("Mathan Playlist from rest assure pojo class");
		requestPlayList.setDescription("Mathan playlist description from rest assure library");
		requestPlayList.setPublic(false);
		
		Response response = PlayListAPI.postCreatePlayList(requestPlayList);
		PlayList responsePlayList = response.as(PlayList.class);
		assertThat(responsePlayList.getName(), equalTo(requestPlayList.getName()));
		assertThat(responsePlayList.getDescription(), equalTo(requestPlayList.getDescription()));
		assertThat(responsePlayList.getPublic(), equalTo(requestPlayList.getPublic()));
		JsonPath jsonRes = response.jsonPath();
		playListID = jsonRes.get("id");
		System.out.println("Play List ID is : "+playListID);
		newRequestName = requestPlayList.getName();
		newRequestDesc = requestPlayList.getDescription();
		newRequestPublic = requestPlayList.getPublic();
		
	}
	
	@Test(priority = 2)
	public void getPlayList() {
		
		Response response = PlayListAPI.getPlayList(playListID);
		PlayList responsePlayList =response.as(PlayList.class);
		assertThat(responsePlayList.getName(), equalTo(newRequestName));
		assertThat(responsePlayList.getDescription(), equalTo(newRequestDesc));
		assertThat(responsePlayList.getPublic(), equalTo(newRequestPublic));
	}
	
	@Test(priority = 3)
	public void updatePlayList() {
		
		PlayList requestPlayList = new PlayList();
		requestPlayList.setName("Mathan Update Playlist from rest assure pojo class");
		requestPlayList.setDescription("Mathan Update playlist description from rest assure library");
		requestPlayList.setPublic(false);
		
		Response response = PlayListAPI.editPlayList(requestPlayList, playListID);
		assertThat(response.statusCode(), equalTo(200));
		
		
		
	}
	
	@Test(priority = 4)
	public void notTocreatePlayList() {
		
		PlayList requestPlayList = new PlayList();
		requestPlayList.setName("");
		requestPlayList.setDescription("Mathan Update playlist description from rest assure library");
		requestPlayList.setPublic(false);
		Response response = PlayListAPI.postCreatePlayList(requestPlayList);
		RootError responseError = response.as(RootError.class);
		assertThat(responseError.getError().getStatus(), equalTo(400));
		assertThat(responseError.getError().getMessage(), equalTo("Missing required field: name"));
		
				
	}

}
