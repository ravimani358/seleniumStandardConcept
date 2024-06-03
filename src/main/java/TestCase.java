import java.time.Duration;

import org.apache.hc.core5.util.Asserts;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase {

	static RemoteWebDriver  driver;
	static WebDriverWait wait;
	static String First_Name;
	static String Family_Name;
	
	
    public static void Launch_browser() {
        
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.get("https://qa-refapp.openmrs.org/openmrs/login.htm");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public static void Login_to_MRS() {
        driver.findElement(By.id("username")).sendKeys("Admin");
        driver.findElement(By.id("password")).sendKeys("Admin123");
        driver.findElement(By.xpath("//li[text()='Inpatient Ward']")).click();
        driver.findElement(By.id("loginButton")).click();
    }
    
    public static void Verify_navigate_to_Dashboard() {
    	String Daskboard_Url= driver.getCurrentUrl();
    	if(Daskboard_Url.contains("home.page")) {
    		System.out.println("Navigate to correct dashboard");
    	}else {
            System.out.println("Failed to navigate to correct dashboard");
            System.exit(0);
    	}
    }
    
    public static void Register_a_patent(){
    	driver.findElement(By.xpath("//a[contains(@href,'registerPatient')]")).click();
    	wait.until(ExpectedConditions.urlContains("registerPatient"));
    	driver.findElement(By.name("givenName")).sendKeys("Test1contact32");
    	driver.findElement(By.name("familyName")).sendKeys("Testing22");
    	driver.findElement(By.id("next-button")).click();
    	
    	//Gender
    	WebElement Gender = driver.findElement(By.id("gender-field"));
    	Select dropdown = new Select(Gender);
    	dropdown.selectByValue("M");
    	driver.findElement(By.id("next-button")).click();
    	
    	// Date of Birth
    	driver.findElement(By.name("birthdateDay")).sendKeys("02");
    	WebElement birth_month = driver.findElement(By.id("birthdateMonth-field"));
    	Select month = new Select(birth_month);
    	month.selectByValue("1");
    	driver.findElement(By.name("birthdateYear")).sendKeys("1997");
    	driver.findElement(By.id("next-button")).click();
    	
    	//Address
    	driver.findElement(By.id("address1")).sendKeys("Address");
    	driver.findElement(By.id("next-button")).click();
    	
    	//phone Number
    	driver.findElement(By.name("phoneNumber")).sendKeys("1199887723");
    	driver.findElement(By.id("next-button")).click();
    	
    	//relationship type
    	WebElement patient_related_field = driver.findElement(By.id("relationship_type"));
    	Select patient_related  = new Select(patient_related_field);
    	patient_related.selectByVisibleText("Doctor");
    	driver.findElement(By.xpath("//input[contains(@placeholder,'Person')]")).sendKeys("patent 1");
    	driver.findElement(By.id("next-button")).click();
    	
    	// Unit Test Assert
    	String Name = driver.findElement(By.xpath("//div[@id='dataCanvas']//p[1]")).getText();
    	String full_name = Name.substring(6).replace(',', ' ');
    	String [] Patent_name = full_name.split("  ");
    	System.out.println(Patent_name[1]);
    	String patient_Gender = driver.findElement(By.xpath("//div[@id='dataCanvas']//p[2]")).getText();
    	String Birthdate = driver.findElement(By.xpath("//div[@id='dataCanvas']//p[3]")).getText();
    	String Address = driver.findElement(By.xpath("//div[@id='dataCanvas']//p[4]")).getText();
    	String Phone_Number = driver.findElement(By.xpath("//div[@id='dataCanvas']//p[5]")).getText();
    	String Relatives = driver.findElement(By.xpath("//div[@id='dataCanvas']//p[6]")).getText();
    	
    	Assert.assertEquals("Test1contact32", Patent_name[0], "Check first name");
    	Assert.assertEquals("Testing22", Patent_name[1], "Check last name");
    	Assert.assertEquals("Male", patient_Gender.substring(8), "Check Address");
    	Assert.assertEquals("Address", Address.substring(9), "Check Address");
    	Assert.assertEquals("1199887723", Phone_Number.substring(14), "Check phone number");
    	
    	//person typeahead
    	driver.findElement(By.id("submit")).click();
    }
    public static void Verify_Patient_details_page() {
    	wait.until(ExpectedConditions.urlContains("patientId"));
    	
    	String patient_url = driver.getCurrentUrl();
    	if(patient_url.contains("patientId")){
    		System.out.println("Navigate to patient page");
    	}else {
    		System.out.println("Failed to navigate to correct dashboard");
            System.exit(0);	
    	}
    	
    	First_Name = driver.findElement(By.className("PersonName-givenName")).getText();
    	Family_Name = driver.findElement(By.className("PersonName-familyName")).getText();
    	
    	System.out.println(First_Name+" "+Family_Name);
    	
    }
    
    public static void Visit() {
    driver.findElement(By.xpath("//div[contains(text(),'Start Visit')]")).click();
    driver.findElement(By.id("start-visit-with-visittype-confirm")).click();
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("start-visit-with-visittype-confirm"))));
    driver.findElement(By.className("PersonName-givenName")).getText();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='referenceapplication.realTime.vitals']/following-sibling::a[1]")));
    driver.findElement(By.xpath("//a[@id='referenceapplication.realTime.vitals']/following-sibling::a[1]")).click();
    wait.until(ExpectedConditions.urlContains("attachments"));
    WebElement fileInput = driver.findElement(By.xpath("//form[contains(@class,'dropzone ng-pristine')]//div[contains(@class,'dz-default dz-message ng-binding')]"));
    System.out.println(fileInput.getText()+">>>>>>>>>>>>>>");
    String filePath = "C:\\Users\\RavishankarN\\eclips_respace\\ST_Selenium\\Attachment\\sample.pdf";
    fileInput.sendKeys(filePath);
    String arrached_comments = "Attachment are added";
    driver.findElement(By.xpath("//textarea[contains(@placeholder,'Enter a caption')]")).sendKeys(arrached_comments);
    driver.findElement(By.xpath("//button[text()='Upload file']")).click();
    String file_upload = driver.findElement(By.xpath("//p[contains(text(),'uploaded.')]")).getText();
    if(file_upload.contains("uploaded")) {
    	System.out.println("File upload successfully uploaded");
    }else {
    	System.out.println("File not uploaded");
        System.exit(0);
    }
    driver.findElement(By.className("PersonName-givenName")).click();
    
    String xpath_1 = "//p[contains(text(),'";
    		
    String xpath_2 = "')]";
    
    driver.findElement(By.xpath(xpath_1+arrached_comments+xpath_2)).isDisplayed();
    driver.findElement(By.xpath("(//div[contains(text(),'End Visit')])[2]")).click();
    
    }
    
    public static void Delete_Patient() throws InterruptedException {
    driver.findElement(By.xpath("//div[contains(text(),'Delete Patient')]")).click();
    driver.findElement(By.id("delete-reason")).sendKeys("This is added to the process");
    driver.findElement(By.xpath("(//button[contains(text(),'Confirm')])[4]")).click();
    Thread.sleep(1000);
    String popup_message = driver.findElement(By.xpath("//p[contains(text(),'deleted')]")).getText();
    if(popup_message.contains("deleted")) {
    	System.out.println("patient are deleted");
    }else {
		System.out.println("Failed to navigate to correct dashboard");
        System.exit(0);
    }
    }
    
    public static void Verify_Patient_Deleted() {
    wait.until(ExpectedConditions.urlContains("findPatient"));
    driver.findElement(By.id("patient-search")).sendKeys(First_Name+" "+Family_Name);
    //wait.until(ExpectedConditions.urlContains("findPatient"));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'No matching records found')]")));
    String Table_result = driver.findElement(By.xpath("//td[contains(text(),'No matching records found')]")).getText();
    if(Table_result.equals("No matching records found")) {
    	System.out.println("Patient details are deleted");
    }else {
		System.out.println("Patient not deleted");
        System.exit(0);
    }
    }
    
    public static void main(String[] args) throws InterruptedException{
        Launch_browser();
        Login_to_MRS();
        Verify_navigate_to_Dashboard();
        Register_a_patent();
        Verify_Patient_details_page();
        Visit();
        Delete_Patient();
        Verify_Patient_Deleted();
    }
}
