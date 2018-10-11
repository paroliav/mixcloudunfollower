package com.parolia.vivek.models;

public class MixCloudURI {

    public static final String REDIRECT_URI = "/house_kidd/following";

    public static final String AUTHORIZE = "https://www.mixcloud.com/oauth/authorize?client_id=<CLIENT_ID>&redirect_uri="+REDIRECT_URI;

    public static final String ACCESS_TOKEN = "https://www.mixcloud.com/oauth/access_token?client_id=<CLIENT_ID>&redirect_uri="+REDIRECT_URI+"&client_secret=<CLIENT_SECRET>&code=<OAUTH_CODE>";

    public static final String MY_INFO = "https://api.mixcloud.com/me/?access_token=<ACCESS_TOKEN>";

    public static final String FOLLOWINGS = "https://api.mixcloud.com/house_kidd/following/?access_token=<ACCESS_TOKEN>&limit=<LIMIT>";

    public static final String UNFOLLOW = "https://api.mixcloud.com/<USER_KEY>/follow/?access_token=<ACCESS_TOKEN>";


    public String getAuthorizeURI(String clientId){
        return AUTHORIZE.replace("<CLIENT_ID>", clientId);
    }

    public String getAccessTokenURI(String clientId, String clientSecret, String oauthCode) {
        return ACCESS_TOKEN.replace("<CLIENT_ID>", clientId).replace("<CLIENT_SECRET>", clientSecret).replace("<OAUTH_CODE>", oauthCode);
    }

    public void extractOauthCode(String url, MixCloudUser user){
        String[] oauthCodeUrl = url.split("=");
        user.setOauthCode(oauthCodeUrl[1]);
    }

    public String getMyInfoURI(String accessToken) {
        return MY_INFO.replace("<ACCESS_TOKEN>", accessToken);
    }

    public String getFollowingsURI(String accessToken, int limit){
        return FOLLOWINGS.replace("<ACCESS_TOKEN>", accessToken).replace("<LIMIT>", Integer.toString(limit));
    }

    public String getUnfollowURI(String accessToken, String userKey){
        return UNFOLLOW.replace("<USER_KEY>", userKey).replace("<ACCESS_TOKEN>", accessToken);
    }

}
