package FlipkartTask;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlipkartIndia {
	public static void main(String[] args) throws IOException, InterruptedException{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://www.flipkart.com");


		// Close login popup
		driver.findElement(By.xpath(("//span[text()='✕']"))).click();


		// Search Bluetooth Speakers
		driver.findElement(By.name("q")).sendKeys("Bluetooth Speakers");
		driver.findElement(By.name("q")).submit();

		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[text()='Brand']")).click();


		// Filter Brand = boAt
		driver.findElement(By.xpath("//div[text()='boAt']")).click();
	    ///driver.findElement(By.xpath("//div[text()='Customer Ratings']")).click();
		// Filter Rating 4★ & above
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[text()='4★ & above']")).click();
		
		Thread.sleep(5000);
		// Sort Low to High
		driver.findElement(By.xpath("//div[text()='Price -- Low to High']")).click();

		Thread.sleep(3000);


		// Open 1st product in new tab
		WebElement firstProduct = driver.findElement(By.xpath("(//a[contains(@href,'/p/')])[1]"));
		Thread.sleep(5000);
		String clickLink = Keys.chord(Keys.CONTROL, Keys.ENTER);
		firstProduct.sendKeys(clickLink);

		Thread.sleep(5000);
		// Switch to new tab
		for (String tab : driver.getWindowHandles()) {
		driver.switchTo().window(tab);
		}
		Thread.sleep(5000);

		// Check Available offers
		List<WebElement> offers = driver.findElements(By.xpath("//span[contains(text(),'Available offers')]"));

		if (offers.size() > 0) {

		List<WebElement> offerList = driver.findElements(By.xpath("//li[contains(@class,'_16eBzU')]"));
		System.out.println("Number of offers: " + offerList.size());
		}


		// Check Add to Cart button
		List<WebElement> addToCart = driver.findElements(By.xpath("//button[text()='Add to Cart']"));

	
		
		if(addToCart.size() > 0) {

		addToCart.get(0).click();

		// Navigate to cart
		driver.get("https://www.flipkart.com/viewcart");
		Thread.sleep(2000);
		// Screenshot
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);

		org.apache.commons.io.FileUtils.copyFile(src,new File("./screenshots/cart_result.png"));

		System.out.println("Product added to cart successfully");

		}

		else {

		System.out.println("Product unavailable — could not be added to cart.");
        Thread.sleep(2000);
		// Screenshot
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);

		org.apache.commons.io.FileUtils.copyFile(src, new File("./screenshots/result.png"));

		}

		driver.quit();

		}

	


}
