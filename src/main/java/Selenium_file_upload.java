import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Selenium_file_upload {

	public static void main(String[] args) throws AWTException, InterruptedException {
		RemoteWebDriver driver;
		WebDriverWait wait;
		
		
      WebDriverManager.chromedriver().setup();
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--disable-notifications");
      driver = new ChromeDriver(options);
      wait = new WebDriverWait(driver, Duration.ofSeconds(20));
      
      driver.get("https://ufile.io/");
      
      driver.findElement(By.xpath("//div[@id='uf-uploader']")).click();
      
      Thread.sleep(5000);
      
      Robot rb = new Robot();
      
      String file_path = "C:\\Users\\RavishankarN\\eclips_respace\\ST_Selenium\\Attachment\\sample.pdf";
      
      StringSelection str = new StringSelection(file_path);
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
      
      rb.keyPress(KeyEvent.VK_CONTROL);
      rb.keyPress(KeyEvent.VK_V);
      rb.keyRelease(KeyEvent.VK_CONTROL);
      rb.keyRelease(KeyEvent.VK_V);
      rb.keyPress(KeyEvent.VK_ENTER);
      rb.keyRelease(KeyEvent.VK_ENTER);
      
      
		

	}

}
