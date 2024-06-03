import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class readfromexcel {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Gmail']")));
        
        String filepath = "C:/Users/RavishankarN/eclips_respace/ST_Selenium/data/excel.xlsx";
        String sheet_name = "Sheet1";
        
        
        XSSFWorkbook workbook = new XSSFWorkbook(filepath);
        XSSFSheet sheet = workbook.getSheet(sheet_name);
        XSSFRow row = sheet.getRow(0);
        XSSFCell cell = row.getCell(0);
        String value = cell.getStringCellValue();   
        driver.findElement(By.xpath("//textarea[@title='Search']")).sendKeys(value);

	}

}
