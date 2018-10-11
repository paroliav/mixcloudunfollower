package com.parolia.vivek.pages;

import com.parolia.vivek.models.MixCloudUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Authorization {

    WebDriver driver;

    public Authorization(WebDriver driver){
        this.driver = driver;
    }

    public static final By EMAIL = By.id("id_email");

    public static final By PASSWORD = By.id("id_password");

    public static final By AUTHORIZE = By.cssSelector("button.btn.btn-secondary");

    public void authorise(MixCloudUser user){
        driver.findElement(EMAIL).sendKeys(user.getUsername());
        driver.findElement(PASSWORD).sendKeys(user.getPassword());

        driver.findElement(AUTHORIZE).click();
    }


}
