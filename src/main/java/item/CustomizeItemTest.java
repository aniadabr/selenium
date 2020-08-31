package item;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomizeItemTest {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
    }

    @After
    public void clean() {
        driver.close();
        driver.quit();
    }

    @Test
    public void changingSize() {
        Select size = new Select(driver.findElement(By.id("group_1")));
        size.selectByVisibleText("S");
        size.selectByVisibleText("M");
        size.selectByVisibleText("L");

    }

    @Test
    public void changingQuantityByWriting() throws NumberFormatException {

        writeQuantity(15);

    }

    @Test
    public void changingQuantityByClick() {

        driver.findElement(By.className("icon-plus")).click();
        WebElement quantityWantedElement = driver.findElement(By.id("quantity_wanted"));
        Assert.assertEquals("2", quantityWantedElement.getAttribute("value"));
        driver.findElement(By.className("icon-minus")).click();
        Assert.assertEquals("1", quantityWantedElement.getAttribute("value"));

    }

    @Test
    public void changingColour() {
        List<WebElement> listOfColours = driver.findElements(By.xpath("//ul[@id='color_to_pick_list']/li"));
        for (WebElement colourElement : listOfColours) {
            colourElement.click();
        }

    }

    public void writeQuantity(int number) {
        WebElement field = driver.findElement(By.id("quantity_wanted"));
        field.clear();
        field.sendKeys(String.valueOf(number));
        driver.findElement(By.id("add_to_cart")).click();
        WebElement quantityText = driver.findElement(By.id("layer_cart_product_quantity"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart_product_quantity")));
        int quantityNumber = Integer.parseInt(quantityText.getText());
        Assert.assertEquals(number, quantityNumber);
    }
}
