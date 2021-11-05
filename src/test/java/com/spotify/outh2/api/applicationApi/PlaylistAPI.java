package com.spotify.outh2.api.applicationApi;

import com.spotify.outh2.Utils.ConfigLoader;
import com.spotify.outh2.api.RestResource;
import com.spotify.outh2.pojos.Playlist;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.outh2.api.Route.PLAYLISTS;
import static com.spotify.outh2.api.Route.USERS;
import static com.spotify.outh2.api.TokenManager.getToken;

public class PlaylistAPI {
    @Step
    public static Response POST(Playlist requestPlayList)
    {
        String endpoint=USERS+"/"+ ConfigLoader.getInstance().getUserId()+PLAYLISTS;
        return RestResource.POST(endpoint,getToken(),requestPlayList);
    }
    public static Response POST(Playlist requestPlayList,String token)
    {
        String endpoint=USERS+"/"+ConfigLoader.getInstance().getUserId()+PLAYLISTS;
        return RestResource.POST(endpoint,token,requestPlayList);
    }
    public static Response GET(String playListId)
    {
        String endpoint=PLAYLISTS+"/"+playListId;
        return RestResource.GET(endpoint,getToken());
    }
    public static Response PUT(String playListId,Playlist requestPlayList)
    {
        String endpoint=PLAYLISTS+"/"+playListId;
        return RestResource.PUT(endpoint,getToken(),requestPlayList);

    }
}
