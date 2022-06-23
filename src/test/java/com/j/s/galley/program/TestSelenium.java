package com.j.s.galley.program;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestSelenium {
    public static void main (String[] args)
    {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://demo.guru99.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gdpr-consent-tool-wrapper")));

        WebElement element = driver.findElement(By.xpath("//input[@name='emailid']"));
        element.sendKeys("abc@gmail.com");

        wait.until(ExpectedConditions.invisibilityOf(popup));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='btnLogin']")));
        button.click();
    }
}
