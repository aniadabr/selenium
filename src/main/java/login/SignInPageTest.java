package login;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SignInPageTest {
    WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void clean() {
        driver.close();
        driver.quit();
    }

    @Test
    public void successSignIn() {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

        driver.findElement(By.id("email")).sendKeys("xogaxi7692@mailboxt.net");
        driver.findElement(By.id("passwd")).sendKeys("Test_123");
        driver.findElement(By.id("SubmitLogin")).click();
        driver.findElement(By.className("info-account"));
    }

    @Test
    public void failedNoPasswordSignIn() {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=identity");
        driver.findElement(By.id("email")).sendKeys("xogaxi7692@mailboxt.net");
        driver.findElement(By.id("SubmitLogin")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div[1]\n"));

    }

    @Test

    public void failedNoEmailSignIn(){
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        driver.findElement(By.id("passwd")).sendKeys("Test_123");
        driver.findElement(By.id("SubmitLogin")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div[1]\n"));
    }
}
