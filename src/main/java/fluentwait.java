import com.google.common.base.Function;

import dev.failsafe.internal.util.Durations;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class fluentwait {

	public static void flue_wait() {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedCondition.)

		Wait<WebDriver> wait2 = new FluentWait<WebDriver>(driver)
		.withTimeout(Duration.ofSeconds(5))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class);

		wait.until(new Function<WebDriver, WebElement>(){
		public WebElement apply(WebDriver driver){
		return driver.findElement(By.xpath(""));
		}
		});
		
		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
		
		wait.until(new Function<WebDriver, WebElement>(){
			
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(""));
			}
			
			
		});
		
		
	}
	
	public static void main(String[] args) {
		flue_wait();
	}
}
