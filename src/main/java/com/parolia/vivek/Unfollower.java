package com.parolia.vivek;

import com.parolia.vivek.models.MixCloudUser;
import com.parolia.vivek.utils.AccessTokenHelper;
import com.parolia.vivek.utils.FollowingHelper;

import java.util.List;
import java.util.Scanner;

public class Unfollower {



    public static void main(String args[]) {
        AccessTokenHelper tokenHelper = new AccessTokenHelper();
        Scanner scanner = new Scanner(System.in);
        MixCloudUser user = new MixCloudUser();

        System.out.println("Hello! Please tell me how many artists you would like to un-follow today?:");
        int unfollowers = scanner.nextInt();



//        tokenHelper.authorizeUser(user);

        tokenHelper.retrieveAccessToken(user);

        FollowingHelper followingHelper = new FollowingHelper();

        System.out.println("You are currently following: "+followingHelper.getFollowingCount(user));

        while(unfollowers > 0 ) {
            List<String> myFollowings;
            if(unfollowers > 100 ) {
                myFollowings = followingHelper.getMyFollowings(user, 100);
                unfollowers = unfollowers - 100;
            } else {
                myFollowings = followingHelper.getMyFollowings(user, unfollowers);
                unfollowers = 0;
            }

            followingHelper.unfollow(myFollowings, user);
        }

        System.out.println("You are now following: "+followingHelper.getFollowingCount(user));
    }

}
