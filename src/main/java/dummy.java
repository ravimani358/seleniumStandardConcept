import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class dummy {

	public static void main(String[] args) throws InterruptedException {
		RemoteWebDriver driver;
		WebDriverWait wait;
		
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-notifications");
//        driver = new ChromeDriver(options);
//        driver.get("https://qa-refapp.openmrs.org/openmrs/login.htm");
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.findElement(By.id("username")).sendKeys("Admin");
//        driver.findElement(By.id("password")).sendKeys("Admin123");
//        driver.findElement(By.xpath("//li[text()='Inpatient Ward']")).click();
//        driver.findElement(By.id("loginButton")).click();
//        driver.get("https://qa-refapp.openmrs.org/openmrs/registrationapp/registerPatient.page?appId=referenceapplication.registrationapp.registerPatient");
//        String Values = driver.findElement(By.xpath("//div[@id='dataCanvas']//p[1]")).getText();
//        System.out.println(Values);
		
	    String xpath_1 = "//p[contains(text(),'";
		
	    String arrached_comments = "Attachment are added";
	    
	    String xpath_2 = "')]";

	    System.out.println(xpath_1+arrached_comments+xpath_2);

	}
}