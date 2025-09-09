package SeleniumJava.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SanjanaTest.pageobjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver= new ChromeDriver();;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//SanjanaTest//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C://Users//sanja//chromedriver-win64//chromedriver.exe");
			//WebDriverManager.chromedriver().setup();
		
			driver = new ChromeDriver();  

		} else {
			System.out.println("No invoke");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		return driver;
	}
	

	@BeforeMethod(alwaysRun = true)
	public LoginPage landingApplication() throws IOException {
		// BaseTest init = new BaseTest();
		initializeDriver();
		// initializeDriver init=driver = initializeDriver();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.goTo();
		return loginpage;

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		// json to string
		String jasonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		// string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jasonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}
}
