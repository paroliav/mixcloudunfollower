package com.parolia.vivek.utils;

import com.parolia.vivek.models.MixCloudURI;
import com.parolia.vivek.models.MixCloudUser;
import com.parolia.vivek.pages.Authorization;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class AccessTokenHelper {

    WebDriver driver;
    public static final String BASE_DIR = System.getProperties().get("user.dir").toString();
    MixCloudURI uriHelper = new MixCloudURI();

    public void authorizeUser(MixCloudUser user) throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(uriHelper.getAuthorizeURI(user.getClientId()));
        System.out.println(driver.getCurrentUrl());
        File srcFiler=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFiler, new File(BASE_DIR+"/screens/Finalscreen.jpg"));

        Authorization authorization = new Authorization(driver);
        authorization.authorise(user);

        uriHelper.extractOauthCode(driver.getCurrentUrl(), user);

        System.out.println(user.getOauthCode());
        driver.quit();
    }

    public void retrieveAccessToken(MixCloudUser user) {
        Response response = RestHelper.get(uriHelper.getAccessTokenURI(user.getClientId(), user.getClientSecret(), user.getOauthCode()));

        String access_token = response.jsonPath().get("access_token");
        user.setAccessToken(access_token);
        System.out.println(user.getAccessToken());
    }


}
