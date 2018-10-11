# mixcloudunfollower
This is a small utility written to unfollow artists on MixCloud using their backend API

Build the JAR using command - 


mvn clean compile assembly:single

Run JAR using - java -jar mixcloud-unfollower.jar


The Unfollower will ask you how many artists you want to unfollow. Provide a number, sit back and relax!!

- Currently allows deletion of 9 artists every 15 minutes due to limitation of API

- Needs authorisation by logging in and finding the ouath code

