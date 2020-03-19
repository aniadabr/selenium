package socialmedia;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SocialMediaURLRedirectionTest {
    WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
    }

    @After
    public void clean() {
        driver.close();
        driver.quit();
    }

    @Test
    public void facebook() {
        testSocialMedia("facebook", "//*[@id=\"seo_h1_tag\"]\n");
    }

    @Test
    public void twitter(){
        testSocialMedia("twitter","/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div/div/div/div[1]/div[2]/div[2]/div/div/div[1]\n");
    }

    @Test
    public void youtube(){
        testSocialMedia("youtube","//*[@id=\"channel-header-container\"]\n");
    }

//Google+ is not tested because is not available

    public void testSocialMedia(String name, String elementXpath){
        driver.findElement(By.className(name)).click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        WebElement groupName = driver.findElement(By.xpath(elementXpath));
        Assert.assertTrue(groupName.getText().contains("Selenium Framework"));
    }


}
