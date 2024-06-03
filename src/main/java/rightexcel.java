import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class rightexcel {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Reduced Duration.ofSeconds(10) to just 10
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Gmail']")));

        WebElement locatedElement = driver.findElement(By.xpath("//a[text()='Gmail']"));
        String title = locatedElement.getText();
        System.out.println(title);

        String filepath = "C:\\Users\\RavishankarN\\eclips_respace\\ST_Selenium\\data\\excel.xlsx";

        File fileName = new File(filepath);
        FileInputStream file = new FileInputStream(fileName);

        try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            if (sheet != null) {
                XSSFRow row = sheet.getRow(0);
                if (row != null) {
                    XSSFCell cell = row.getCell(0);
                    if (cell != null) {
                        cell.setCellValue(title);
                    } else {
                        row.createCell(0).setCellValue(title);
                    }
                }
            }
            try (FileOutputStream outputStream = new FileOutputStream(filepath)) {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.close();
            driver.quit();
        }
    }
}
