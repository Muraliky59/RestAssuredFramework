package com.spotify.outh2.tests;

import com.spotify.outh2.Utils.DataLoader;
import com.spotify.outh2.api.StatusCode;
import com.spotify.outh2.api.applicationApi.PlaylistAPI;
import com.spotify.outh2.pojos.Error;
import com.spotify.outh2.pojos.InnerError;
import com.spotify.outh2.pojos.Playlist;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.outh2.Utils.fakerUtility.generateDescription;
import static com.spotify.outh2.Utils.fakerUtility.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
@Epic("Spotify Oauth2.0")
@Feature("Playlist API")
public class PlaylistTests extends BaseTest {
    @Step
    public Playlist playListBuilder(String name,String description,boolean _public)
    {
        return Playlist.builder().name(name).description(description)._public(_public).build();

    }
    @Step
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode)
    {
        assertThat(actualStatusCode,equalTo(statusCode.code));
    }
    @Step
    public void assertResponsePlayList(Playlist response,Playlist request)
    {
        assertThat(response.getName(),equalTo(request.getName()));
        assertThat(response.getDescription(),equalTo(request.getDescription()));
        assertThat(response.get_public(),equalTo(request.get_public()));
    }
    public Error errorBuilder(StatusCode statusCode)
    {
        InnerError innerError=new InnerError();
        innerError.setStatus(statusCode.code);
        innerError.setMessage(statusCode.msg);
        Error error=new Error();
        error.setError(innerError);
        return error;
    }
    public void assertErrorResponse(Error response,Error request)
    {
        assertThat(response.getError().getStatus(),equalTo(request.getError().getStatus()));
        assertThat(response.getError().getMessage(),equalTo(request.getError().getMessage()));
    }
    @Story("Create Playlist")
    @Test(description = "Should be able to create playlist")
    @Description("This test is used to create playlist")
    @Link("httsp://example.org")
    @Link(name = "allure",type = "myink")
    @Issue("1234")
    @TmsLink("2356")

    public void createPlayList()
    {
        Playlist requestPlayList=playListBuilder(generateName(),generateDescription(),false);
        Response response= PlaylistAPI.POST(requestPlayList);
        assertStatusCode(response.statusCode(),StatusCode.STATUS_CODE_201);
        assertResponsePlayList(response.as(Playlist.class),requestPlayList);
    }
    @Test
    public void getPlayList()
    {
        String playListId= DataLoader.getInstance().getUserId();
        Playlist requestPlayList=playListBuilder("Updated Playlist Name","Updated playlist description",false);
        Response response=PlaylistAPI.GET(playListId);
        assertStatusCode(response.statusCode(),StatusCode.STATUS_CODE_200);
        assertResponsePlayList(response.as(Playlist.class),requestPlayList);

    }
    @Test
    public void updatePlayList()
    {
        String playListId=DataLoader.getInstance().updateUserId();
        Playlist requestPlayList=playListBuilder(generateName(),generateDescription(),false);
        Response response=PlaylistAPI.PUT(playListId,requestPlayList);
        assertStatusCode(response.statusCode(),StatusCode.STATUS_CODE_200);
    }
    @Story("Create Playlist")
    @Test
    public void sholudNotCreatePlayList()
    {
        Playlist requestPlayList=playListBuilder("",generateDescription(),false);
        Error error=errorBuilder(StatusCode.STATUS_CODE_400);
        Response response= PlaylistAPI.POST(requestPlayList);
        assertStatusCode(response.statusCode(),StatusCode.STATUS_CODE_400);
        assertErrorResponse(response.as(Error.class),error);
    }
    @Story("Create Playlist")
    @Test
    public void invalidAccessToken()
    {
        String token="12345";
        Playlist requestPlayList=playListBuilder(generateName(),generateDescription(),false);
        Error error=errorBuilder(StatusCode.STATUS_CODE_401);
        Response response= PlaylistAPI.POST(requestPlayList,token);
        assertStatusCode(response.statusCode(),StatusCode.STATUS_CODE_401);
        assertErrorResponse(response.as(Error.class),error);

    }
}
