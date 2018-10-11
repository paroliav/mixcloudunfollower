package com.parolia.vivek.utils;

import com.parolia.vivek.models.MixCloudURI;
import com.parolia.vivek.models.MixCloudUser;
import io.restassured.response.Response;

import java.time.LocalDateTime;
import java.util.List;

public class FollowingHelper {
    
    MixCloudURI uriHelper = new MixCloudURI();

    public String getFollowingCount(MixCloudUser user){
        Response response = RestHelper.get(uriHelper.getMyInfoURI(user.getAccessToken()));

        String followingCount = response.jsonPath().get("following_count").toString();
        return followingCount;
    }

    public List<String> getMyFollowings(MixCloudUser user, int limit){
        Response response = RestHelper.get(uriHelper.getFollowingsURI(user.getAccessToken(), limit));

        List<String> usernames = response.jsonPath().getList("data.username");
        for(String username : usernames) {
            System.out.println(username);
        }
        return usernames;
    }

    public void unfollow(List<String> followings, MixCloudUser user) {
        for(String userKey : followings) {
            Response response = RestHelper.delete(uriHelper.getUnfollowURI(user.getAccessToken(), userKey));

            if(response.jsonPath().get("result.success") == null){
                Integer retryAfter = response.jsonPath().get("error.retry_after");
                try {
                    System.out.println("Un-follow will resume in : "+(retryAfter)/60+" mins");
                    Thread.sleep((retryAfter + 1) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                RestHelper.delete(uriHelper.getUnfollowURI(user.getAccessToken(), userKey));
            }

        }
    }

}
