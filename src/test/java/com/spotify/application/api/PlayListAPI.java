package com.spotify.application.api;
import com.restAssure.api.RestAssureAPIWrapper;
//import com.restAssure.api.RestAssureAPIWrapper;
import com.spotify.pojo.PlayList;

//import com.utilities.PropertiesReader;
import io.restassured.response.Response;

public class PlayListAPI {
	//public static String fileName = "environment";
	private static String userid = "31arqryw45n666qdjjrr62rn6yfa";
		
	public static Response postCreatePlayList(PlayList requestPlaylist) {
		return RestAssureAPIWrapper.post("/users/"+userid+"/playlists", requestPlaylist);
		}
	
	public static Response getPlayList(String playListId) {
		return RestAssureAPIWrapper.get("/playlists/"+playListId);
		}
	
	public static Response editPlayList(PlayList requestPlaylist,String playListId) {
		return RestAssureAPIWrapper.update("/playlists/"+playListId, requestPlaylist);
		}
	

}
